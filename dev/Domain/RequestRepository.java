//package Domain;
//
//
//import DAL.RequestDAOImpl;
//import com.fasterxml.jackson.databind.JsonNode;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class RequestRepository implements IRepository<Request, Pair> {
//    private final Map<Pair, Request> requests;
//    private RequestDAOImpl dao;
//
//    private RequestRepository() {
//        requests = new HashMap<>();
//        dao = new RequestDAOImpl();
//
//    }
//    private static class InRequestHolder {
//        private final static RequestRepository INSTANCE = new RequestRepository();
//    }
//
//    /**
//     * public static method that returns the single instance of the class
//     */
//
//    public static RequestRepository getInstance() {
//        return RequestRepository.InRequestHolder.INSTANCE;
//    }
//
//    @Override
//    public void add(Request request) {
//        Pair<Integer,Integer> requestKey = new Pair<>(request.getWorker().getId(), request.getWeek());
//        if (requests.containsKey(requestKey) || dao.search(requestKey) != null) {
//            throw new IllegalArgumentException("Request already exists");
//        }
//        requests.put(requestKey, request);
//        dao.insert(JsonNodeConverter.toJsonNode(request));
//
//    }
//
//
//    public void editRequest(Request request) throws Exception {
//        Pair requestKey = new Pair<>(request.getWorker().getId(), request.getWeek());
//        try {
//            dao.update(JsonNodeConverter.toJsonNode(request));
//        } catch (Exception e) {
//            throw new Exception("Failed to update request");
//        }
//        requests.put(requestKey, request);
//    }
//
//    public Request[] getAllRequests() {
//        return requests.values().toArray(new Request[0]);
//    }
//
//
//
//    @Override
//    public Request get(Pair key){
//        if(requests.containsKey(key))
//            return requests.get(key);
//        JsonNode jn = dao.search(key);
//        if (jn != null) {
//            return JsonNodeConverter.fromJsonNode(jn, Request.class);
//        }
//        return null;
//
//    }
//
//    @Override
//    public void remove(Pair key) {
//        if(key != null){
//            try{
//                dao.remove(key);
//                requests.remove(key);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//
//
//}