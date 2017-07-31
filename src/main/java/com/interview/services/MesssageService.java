package com.interview.services;

import com.interview.dao.MessageRepository;
import com.interview.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by robert.j.ssemmanda on 29/07/2017.
 */

@Service
public class MesssageService {

    @Autowired
    private MessageRepository messageRepository;


    public  List<MessageModel>findAll(){
        List<MessageModel>toReturn = messageRepository.findAll();//new ArrayList<>();
        /*for(MessageModel model : messageRepository.findAll()){
            toReturn.add(model);
        }*/
        return toReturn;
    }

    public List<MessageModel> findAll(int offset, int limit, Map<String, Boolean> sortOrders) {
        int page = offset / limit;
        List<Sort.Order> orders = sortOrders.entrySet().stream()
                .map(e -> new Sort.Order(e.getValue() ? Sort.Direction.ASC : Sort.Direction.DESC, e.getKey()))
                .collect(Collectors.toList());

        PageRequest pageRequest = new PageRequest(page, limit, orders.isEmpty() ? null : new Sort(orders));
        Page temp = messageRepository.findAll(pageRequest);
        //List<MessageModel> items = temp.getContent();
        return temp.getContent().subList(offset%limit, temp.getContent().size());
        //return items;
    }

    public List<MessageModel> findAll(int offset, int limit) {
        int page = offset / limit;
        PageRequest pageRequest = new PageRequest(page, limit);
        Page temp = messageRepository.findAll(pageRequest);
        List<MessageModel> items = temp.getContent();
        //List<MessageModel> items = messageRepository.findAll(pageRequest);
        //System.err.println(items);
        return temp.getContent().subList(offset%limit, temp.getContent().size());
        //return items;
    }

    public int count(){
        return Math.toIntExact(messageRepository.count());
    }

}
