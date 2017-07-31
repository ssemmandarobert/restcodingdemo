package com.interview.dao;

import com.interview.model.MessageModel;
import com.interview.model.MessageStatics;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by robert.j.ssemmanda on 29/07/2017.
 * We use this class to save data to an in memory db
 * and also retrieve it from and in memory db
 * for ui display
 *
 */
@Repository
public interface MessageRepository extends PagingAndSortingRepository<MessageModel, Long> {
    public List<MessageModel> findAll();

    @Query(value = "select new com.interview.model.MessageStatics(v.currencyFrom, count(v)) from MessageModel v group by v.currencyFrom")
    public List<MessageStatics> findCurrencyFromCount();

    @Query(value = "select new com.interview.model.MessageStatics(v.originatingCountry, count(v)) from MessageModel v group by v.originatingCountry")
    public List<MessageStatics> findCountryCount();

    @Query(value = "select new com.interview.model.MessageStatics(v.currencyTo, count(v)) from MessageModel v group by v.currencyTo")
    public List<MessageStatics> findCurrencyToCount();

    @Query(value = "select new com.interview.model.MessageStatics(v.userId, count(v)) from MessageModel v group by v.userId")
    public List<MessageStatics> findUserIdTransCount();

}
