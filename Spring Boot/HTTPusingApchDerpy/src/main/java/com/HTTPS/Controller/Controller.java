package com.HTTPS.Controller;

import com.HTTPS.Modell.Topic;
import com.HTTPS.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    private TopicService topicService;

    // Get
    @RequestMapping("/topics")
    public List<Topic> getAllTopics() {
        return topicService.getTopics();
    }

    //Get using Filter
    @RequestMapping("/topics/{id}")
    public Optional<Topic> getTopic(@PathVariable String id) {
        return topicService.getTopic(id);

    }

    //Post
    @RequestMapping(method = RequestMethod.POST, value = "/topics ")
    public void addTopic(@RequestBody Topic topic) {
        topicService.addTopic(topic);
    }

    //Put using filter
    @RequestMapping(method = RequestMethod.PUT, value = "/topics/{id} ")
    public void updateTopic(@RequestBody Topic topic, @PathVariable String id) {
        topicService.updateTopic(topic);
    }

    //Delete  using filter
    @RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id} ")
    public void deleteTopic( @PathVariable String id) {
        topicService.deleteTopic(id);
    }

}
