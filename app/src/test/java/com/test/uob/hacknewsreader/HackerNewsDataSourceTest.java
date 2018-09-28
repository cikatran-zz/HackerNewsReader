package com.test.uob.hacknewsreader;

import com.test.uob.hacknewsreader.api.HackerNewsReaderService;
import com.test.uob.hacknewsreader.dagger.BasicLogicTest;
import com.test.uob.hacknewsreader.modal.Comment;
import com.test.uob.hacknewsreader.modal.Story;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

@RunWith(MockitoJUnitRunner.class)
public class HackerNewsDataSourceTest extends BasicLogicTest{
    @Inject
    HackerNewsReaderService hackerNewsReaderService;

    Integer[] kidsArray = {
            18063362,
    };
//
    ArrayList<Integer> kids = new ArrayList<>(Arrays.asList(kidsArray));
//    private Story story = new Story();
//    private Comment comment = new Comment();
//    private List<Story> testTopStoriesList = new ArrayList<>();
//    private List<Comment> testCommentList = new ArrayList<>();

    @Override
    public void setUp() throws Exception {
        getComponent().inject(this);
        super.setUp();
//        //setup Story
//        story.setBy("garretruh");
//        story.setDescendants(216);
//        story.setId(18062963);
//        story.setKids(kids);
//        story.setScore(465);
//        story.setTime(1537840438);
//        story.setTitle("Instagram’s Co-Founders Said to Step Down from Company");
//        story.setType("story");
//        story.setUrl("https://www.nytimes.com/2018/09/24/technology/instagram-cofounders-resign.html");
//
//        testTopStoriesList.add(story);
//
//        //setup Comment
//        comment.setBy("throwaway724");
//        comment.setId(18063362);
//        comment.setParent(18062963);
//        comment.setType("comment");
//
//        testCommentList.add(comment);
    }

    @Test
    public void getTopStoriesObservable() {

        HackerNewsDataSource hackerNewsDataSource = new HackerNewsDataSource(hackerNewsReaderService);
        TestObserver<List<Story>> observer = hackerNewsDataSource.getTopStoryItems().test();
        observer.assertNoErrors();
        observer.awaitTerminalEvent();
        observer.assertComplete();
        observer.assertValue(testTopStoriesList -> testTopStoriesList.get(0).getBy().equals("garretruh"));
    }

    @Test
    public void getStoryDetail() {
        HackerNewsDataSource hackerNewsDataSource = new HackerNewsDataSource(hackerNewsReaderService);
        TestSubscriber<Story> observer = hackerNewsDataSource.getStory(18062963).test();
        observer.assertNoErrors();
        observer.awaitTerminalEvent();
        observer.assertComplete();
        observer.assertValue(story -> story.getBy().equals("garretruh"));
        observer.assertValue(story -> story.getId() == 18062963);
    }

    @Test
    public void getComment() {
        HackerNewsDataSource hackerNewsDataSource = new HackerNewsDataSource(hackerNewsReaderService);
        TestSubscriber<Comment> observer = hackerNewsDataSource.getComment(18063362).test();
        observer.assertNoErrors();
        observer.awaitTerminalEvent();
        observer.assertComplete();
        observer.assertValue(comment -> comment.getBy().equals("throwaway724"));
        observer.assertValue(comment -> comment.getParent() == 18062963);
        observer.assertValue(comment -> comment.getType().equalsIgnoreCase("comment"));
        observer.assertValue(comment -> comment.getId() == 18063362);
        observer.assertValue(comment -> comment.getComment().getId() == 18064203);
        observer.assertValue(comment -> comment.getComment().getParent() == 18063362);
        observer.assertValue(comment -> comment.getComment().getBy().equals("laurieg"));
    }

    @Test
    public void getSubComment() {
        HackerNewsDataSource hackerNewsDataSource = new HackerNewsDataSource(hackerNewsReaderService);
        TestSubscriber<Comment> observer = hackerNewsDataSource.getSubComment(18064203).test();
        observer.assertNoErrors();
        observer.awaitTerminalEvent();
        observer.assertComplete();
        observer.assertValue(comment -> comment.getBy().equals("laurieg"));
        observer.assertValue(comment -> comment.getParent() == 18063362);
        observer.assertValue(comment -> comment.getType().equalsIgnoreCase("comment"));
        observer.assertValue(comment -> comment.getId() == 18064203);

    }

    @Test
    public void getCommentList() {
        HackerNewsDataSource hackerNewsDataSource = new HackerNewsDataSource(hackerNewsReaderService);
        TestObserver<List<Comment>> observer = hackerNewsDataSource.getComments(kids).test();
        observer.assertNoErrors();
        observer.awaitTerminalEvent();
        observer.assertComplete();
        observer.assertValue(comments -> comments.get(0).getBy().equals("throwaway724"));
    }
}
