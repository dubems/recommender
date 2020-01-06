package com.nriagudubem.recommenderspring.entity.repository;

import com.nriagudubem.recommenderspring.entity.model.Book;
import com.nriagudubem.recommenderspring.entity.model.Feedback;
import com.nriagudubem.recommenderspring.entity.model.FeedbackType;
import com.nriagudubem.recommenderspring.entity.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface FeedbackRepository extends CrudRepository<Feedback, UUID> {

    boolean existsByBook_ASINAndFeedbackTypeAndUser(Long ASIN, FeedbackType type, User user);

    List<Feedback> findAllByUser(User user);

    Feedback findByBook(Book book);
}
