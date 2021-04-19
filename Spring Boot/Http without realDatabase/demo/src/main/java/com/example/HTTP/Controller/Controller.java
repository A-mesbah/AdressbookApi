package com.example.demo.Controller;

import com.example.demo.Service.Topic;
import com.example.demo.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;

@RestController
public class Controller {
    @Autowired
    private TopicService topicService;
    // Get
    @RequestMapping("/topics")
    public List<Topic> getAllTopics(){
        return topicService.getTopics();
    }
    //Get using Filter
    @RequestMapping("/topics/{id}")
    public Topic getTopic(@PathVariable String id ){
       return topicService.getTopic(id);

    }
    //Post
    @RequestMapping (method = RequestMethod.POST , value = "/topics ")
    public void addTopic(@RequestBody Topic topic ){
        topicService.addTopic (topic);
    }
    //Put using filter
    @RequestMapping(method = RequestMethod.PUT , value = "/topics/{id} ")
    public void updateTopic(@RequestBody Topic topic, @PathVariable String id){
        topicService.updateTopic (id ,topic);
    }
    //Delete  using filter
    @RequestMapping(method = RequestMethod.DELETE , value = "/topics/{id} ")
    public void deleteTopic(@RequestBody Topic topic, @PathVariable String id){
        topicService.deleteTopic (id ,topic);
    }

}
