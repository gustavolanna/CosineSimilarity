import exception.ObjectNotFoundException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Program entry point. Starts up and runs the servlet. Defines the servlet 
 * end points and delegate request processing.
 * 
 * AVOID CHANGING ANYTHING IN THIS FILE. If you do make changes please provide justification.
 */
public class Main
{
	
	/**
	 * Initializes and runs the jetty servlet.
	 * @param args
	 * @throws Exception
	 */
    public static void main( String[] args ) throws Exception
    {
        // Create a basic jetty server object that will listen on port 8080.
        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(SimCalcServlet.class, "/*");
        server.start();

        // The use of server.join() the will make the current thread join and
        // wait until the server is done executing.
        server.join();
    }

    /**
     * Servlet definition. Implements handlers for the supported http methods. The endpoints are:
     * 
     * GET
     *     /termFrequencies
     *           documentText="url encoded text of document"
     *           
     *     /similarityScore
     *           documentText1="url encoded text of first document"
     *           documentText2="url encoded text of second document"
     *           
     *     /genreDocuments
     *           genre="name of genre"
     *           
     *     /nClosestGenres
     *           documentText="url encoded text of document"
     *           count="maximum number of genres to return in response"
     *     
     * PUT
     *     /genreDocument
     *           genre="name of genre"
     *           docId="id of document"
     *           documentText="url encoded text of document"
     *     
     * DELETE
     *     /genreDocument
     *           genre="name of genre"
     *           docId="id of document"
     */
    @SuppressWarnings("serial")
    public static class SimCalcServlet extends HttpServlet
    {
    		private RequestHandler requestHandler = new RequestHandler();

    		/**
    		 * Handle http get. Dispatch the handling of requests for the supported rest end points
    		 */
        @Override
        protected void doGet( HttpServletRequest request,
                              HttpServletResponse response ) throws ServletException,
                                                            IOException
        {
            try {
				response.setContentType("application/json");
				response.setStatus(HttpServletResponse.SC_OK);

				if (request.getPathInfo().equals("/termFrequencies")) {
					String docText = request.getParameter("documentText");
					Map<String, Integer> termFrequencies = requestHandler.getTermFrequencies(docText);
					String responseBody = intMapToJson(termFrequencies);
					response.getWriter().println(responseBody);
				} else if (request.getPathInfo().equals("/similarityScore")) {
					String doc1Text = request.getParameter("documentText1");
					String doc2Text = request.getParameter("documentText2");
					Double similarityScore = requestHandler.getSimilarityScore(doc1Text, doc2Text);
					response.getWriter().println(similarityScore.toString());
				} else if (request.getPathInfo().equals("/genreDocuments")) {
					String genre = request.getParameter("genre");
					List<String> docIds = requestHandler.getDocumentsInGenre(genre);
					String responseBody = stringListToJson(docIds);
					response.getWriter().println(responseBody);
				} else if (request.getPathInfo().equals("/nClosestGenres")) {
					String documentText = request.getParameter("documentText");
					String count = request.getParameter("count");
					List<String> genres = requestHandler.getNClosestGenres(documentText, Integer.parseInt(count));
					String responseBody = stringListToJson(genres);
					response.getWriter().println(responseBody);
				} else {
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				}

			} catch (IllegalArgumentException e) {
            	handleException(response, e, HttpServletResponse.SC_BAD_REQUEST);
			} catch (ObjectNotFoundException e) {
				handleException(response, e, HttpServletResponse.SC_NOT_FOUND);
            } catch (Exception e) {
				handleException(response, e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }

		/**
		 * Handle http put. Dispatch the handling of requests for the supported rest end points
		 */
        @Override
        protected void doPut(HttpServletRequest request, HttpServletResponse response)
        		throws ServletException, IOException
        {
            try {
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_OK);
                
        			if (request.getPathInfo().equals("/genreDocument")) {
        				String genre = request.getParameter("genre");
        				String docId = request.getParameter("docId");
        				String documentText = request.getParameter("documentText");
        				requestHandler.addDocumentToGenre(genre, docId, documentText);
        			} else {
                		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        			}
			} catch (IllegalArgumentException e) {
				handleException(response, e, HttpServletResponse.SC_BAD_REQUEST);
			} catch (Exception e) {
				handleException(response, e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
        }

        
		/**
		 * Handle http delete. Dispatch the handling of requests for the supported rest end points
		 */
        @Override
        protected void doDelete(HttpServletRequest request, HttpServletResponse response)
        		throws ServletException, IOException
        {
        		try {
        			response.setContentType("application/json");
        			response.setStatus(HttpServletResponse.SC_OK);        			

        			if (request.getPathInfo().equals("/genreDocument")) {
        				String genre = request.getParameter("genre");
        				String docId = request.getParameter("docId");
        				requestHandler.removeDocumentFromGenre(genre, docId);
        			} else {
                		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        			}
				} catch (IllegalArgumentException e) {
					handleException(response, e, HttpServletResponse.SC_BAD_REQUEST);
				} catch (ObjectNotFoundException e) {
					handleException(response, e, HttpServletResponse.SC_NOT_FOUND);
				} catch (Exception e) {
					handleException(response, e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
        }

		private void handleException(HttpServletResponse response, Exception exception, int httpStatus) throws IOException {
			response.setContentType("text/plain");
			response.setStatus(httpStatus);
			response.getWriter().println(exception.getMessage());
		}

        /**
         * Serialize a Map<String,Integer> to json:
         * '{key1:value1, key2:value2, ...}'
         */
        private String intMapToJson(Map<String, Integer> map) {
        		StringBuilder result = new StringBuilder();
        		result.append('{');
        		
        		map.forEach((k,v) -> {
        			result.append('"').append(k).append("\":").append(v).append(',');
        		});
        		
        		if (result.length() > 1) {
        			result.deleteCharAt(result.length() -1); // remove trailing comma
        		}
        		
        		result.append('}');
        		
        		return result.toString();
        }
        
        /**
         * Serialize a List<String> to json:
         * '["item1","item2","item3"]'
         * @param list
         * @return
         */
        private String stringListToJson(List<String> list) {
        		StringBuilder result = new StringBuilder();
        		result.append('[');
        		
        		list.forEach((item) -> {
        			result.append('"').append(item).append("\",");
        		});
        		
        		if (result.length() > 1) {
        			result.deleteCharAt(result.length() -1); // remove trailing comma
        		}
        		
        		result.append(']');
        		
        		return result.toString();        		
        }
    }
}
