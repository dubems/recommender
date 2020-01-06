package com.nriagudubem.recommenderspring.entity;

import com.nriagudubem.recommenderspring.entity.model.Book;
import com.nriagudubem.recommenderspring.entity.repository.BookRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Component
@Log4j2
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    private final List<List<Object>> recommendations = Arrays.asList(
            Arrays.asList(2182718L, "Improve Your Bowls", "Tony Allcock", "Sports & Outdoors"),
            Arrays.asList(7210361L, "Asthma-Free Naturally: Everything You Need to Know About Taking Control of Your Asthma--Featuring the Buteyko Breathing Method Suitable for Adults and Children", "Patrick G. McKeown", "Health Fitness & Dieting"),
            Arrays.asList(7262833L, "Seeing Red", "Graham Poll", "Biographies & Memoirs"),
            Arrays.asList(7444397L, "Dare to Dream: Life as One Direction", "One Direction", "Teen & Young Adult"),
            Arrays.asList(26533839L, "Responsible Driving Student Edition Softcover", "McGraw-Hill Education", "Test Preparation"),
            Arrays.asList(28600673L, "The Unofficial Guide to Ethnic Cuisine and Dining in America", "Eve Zibart", "Travel"),
            Arrays.asList(28631188L, "Webster's New World College Dictionary Indexed Fourth Edition", "The Editors of the Webster's New World Dictionaries", "Reference"),
            Arrays.asList(28639235L, "Complete Idiot's Guide to Looking and Feeling Younger", "Hattie", "Health Fitness & Dieting"),
            Arrays.asList(28644611L, "The Complete Idiot's Guide to the NBA", "Steven D. Strauss", "Sports & Outdoors"),
            Arrays.asList(28740173L, "The Emotional Life of the Toddler", "Alicia F. Lieberman", "Self-Help"),
            Arrays.asList(29290406L, "Science And Human Behavior", "B.F Skinner", "Medical Books")
    );

    @Override
    public void run(String... args) throws Exception {
        final Instant now = Instant.now();
        recommendations.forEach((item) ->
                Book.builder().title(item.get(1).toString())
                        .genre(item.get(3).toString())
                        .author(item.get(2).toString())
                        .ASIN((Long) item.get(0))
                        .createdAt(now)
                        .build()
        );
        log.info("Bootstrapped application!");
    }
}
