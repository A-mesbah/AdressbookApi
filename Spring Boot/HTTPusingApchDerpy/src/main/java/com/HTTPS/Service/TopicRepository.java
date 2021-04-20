package com.HTTPS.Service;

import com.HTTPS.Modell.Topic;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic,String> {
}
