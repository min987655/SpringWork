package com.project.brunch.service.crawling.post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.project.brunch.domain.post.Post;
import com.project.brunch.domain.user.User;

// 작가 아이디명(snsId), 닉네임 크롤링해서 User 에 담기

@Service
public class NowService {

	private static String postId = "1";
	private static String host = "https://brunch.co.kr/";
	private static String URL;
	private static User user;
	private static Elements element;
	private static Elements elCover;
	private static Elements elBody;
	private static Elements elWriter;
	private static Post post;

	public List<Post> getBrunchDatas() throws IOException {

		List<Post> postList = new ArrayList<>();
//		User userId = (User) model.getAttribute("senduser");
//		String userId = "pistol4747";
		
		while (true) {
//			URL = host + "@" + userId.getSnsId() + "/"+ postId;
			URL = host + "@ws820512" + "/"+ postId;
			Document doc = Jsoup.connect(URL).get();
			element = doc.select(".wrap_view_article");
			elCover = element.select(".wrap_cover");
			elBody = element.select(".wrap_body");
			elWriter = element.select("#wrapArticleInfo");

			String title = elCover.select("h1.cover_title").text(); // title
			String subTitle = elCover.select("p.cover_sub_title").text(); // subTitle
			String nickname = elWriter.select("span.text_author").text();

			postId = NowUtil.increase(postId);

			String content = elBody.select("h4").text();
			Elements picture = elBody.select(".wrap_img_float img[src]");
			for (Element pics : picture) {
				String pic = pics.attr("src");
				System.out.println("사진 ::: " + pic);
			}

			post = Post.builder().title(title).subTitle(subTitle).content(content).build();
			postList.add(post);
			System.out.println(postList);
			return postList;
			
		}
	}

}
