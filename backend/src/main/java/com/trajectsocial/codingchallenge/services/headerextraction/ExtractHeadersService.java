package com.trajectsocial.codingchallenge.services.headerextraction;

//import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trajectsocial.codingchallenge.entities.SiteHeader;
import com.trajectsocial.codingchallenge.entities.User;
import com.trajectsocial.codingchallenge.repositories.SiteHeaderRepository;
import com.trajectsocial.codingchallenge.repositories.UserRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExtractHeadersService {

    @Autowired
    private SiteHeaderRepository siteHeaderRepository;

    @Autowired
    private UserRepository userRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    public ExtractHeadersService(){
        objectMapper.enable(com.fasterxml.jackson.core.JsonGenerator.Feature.IGNORE_UNKNOWN);
    }

    private boolean skipShortener(){
        String s = System.getProperty("skipBitly");
        return s != null && s.equals("true");
    }

    public void extractAndSaveHeaders(String url, Long userId) throws Exception {
        if (url == null || url.isEmpty()){
            return;
        }
        List<String> h1List = new ArrayList<>();
        List<String> h2List = new ArrayList<>();
        List<String> h3List = new ArrayList<>();

        String shortenedUrl = null;
        if (skipShortener()){
            shortenedUrl = url;
        }
        else {
            shortenedUrl = getShortenedLink(url);
        }

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()){
            throw new IllegalArgumentException("invalid user_id");
        }
        User user = optionalUser.get();
        user.setShortUrl(shortenedUrl);
        userRepository.save(user);

        Document doc = Jsoup.connect(url).get();

        Elements headers = doc.select("h1");
        for (Element headline : headers) {
            String text = extractTextFromElement(headline);
            h1List.add(text);
        }

        headers = doc.select("h2");
        for (Element headline : headers) {
            String text = extractTextFromElement(headline);
            h2List.add(text);
        }

        headers = doc.select("h3");
        for (Element headline : headers) {
            String text = extractTextFromElement(headline);
            h3List.add(text);
        }

        for (String h : h1List){
            SiteHeader siteHeader = new SiteHeader();
            siteHeader.setUserId(userId);
            siteHeader.setText(h);
            siteHeaderRepository.save(siteHeader);
        }

        for (String h : h2List){
            SiteHeader siteHeader = new SiteHeader();
            siteHeader.setUserId(userId);
            siteHeader.setText(h);
            siteHeaderRepository.save(siteHeader);
        }

        for (String h : h3List){
            SiteHeader siteHeader = new SiteHeader();
            siteHeader.setUserId(userId);
            siteHeader.setText(h);
            siteHeaderRepository.save(siteHeader);
        }
    }

    private String extractTextFromNode(Node node){
        if (node.childNodes().isEmpty()){
            return node.toString();
        }
        for (Node n : node.childNodes()){
            String s = extractTextFromNode(n);
            if (s != null){
                return s;
            }
        }
        return null;

    }

    private String extractTextFromElement(Element element){
        if (element.childNodes().isEmpty()){
            return element.text().toLowerCase();
        }
        for (Node node : element.childNodes()){
            String text = extractTextFromNode(node);
            if (text != null){
                return text.toLowerCase();
            }
        }
        return null;
    }

    private String getShortenedLink(String link) throws Exception {
//        URL url = new URL("https://api-ssl.bitly.com/v4/shorten");
//
//        HttpURLConnection con = (HttpURLConnection)url.openConnection();
//        con.setRequestMethod("POST");
//
//        con.setRequestProperty("Authorization", "Bearer 31af09df449cf2cc8caccde6a3f6c491cd11a65d");
//        con.setRequestProperty("Content-Type", "application/json");
//        con.setRequestProperty("Accept", "application/json");
//
//        con.setDoOutput(true);
//
//        String jsonInputString = "{ \"domain\": \"bit.ly\"  , \"long_url\": \"" + link + "\" }";
//
//        try(OutputStream os = con.getOutputStream()){
//            byte[] input = jsonInputString.getBytes("utf-8");
//            os.write(input, 0, input.length);
//        }
//
//        int code = con.getResponseCode();
//        if (code >= 300){
//            throw new RuntimeException("error processing the website.");
//        }
//
//        StringBuilder response = new StringBuilder();
//        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))){
//
//            String responseLine = null;
//            while ((responseLine = br.readLine()) != null) {
//                response.append(responseLine.trim());
//            }
//
//        }
//
//        JsonNode jsonNode = objectMapper.readTree(response.toString());
//
//        return jsonNode.get("link").asText();
        return link;
    }


}
