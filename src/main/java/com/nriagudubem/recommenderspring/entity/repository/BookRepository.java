package com.nriagudubem.recommenderspring.entity.repository;

import com.nriagudubem.recommenderspring.entity.model.Book;
import com.nriagudubem.recommenderspring.entity.model.Feedback;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, UUID> {

    Stream<Book> findAllByFeedbackNotIn(List<Feedback> feedbacks, PageRequest request);

}
