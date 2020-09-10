package crawnewsapp;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

// 1. Jsoup (maven)
// 2. Jsoup로 URL 요청
// 3. oid의 번호는 어디까지 있는지
// 4. oid마다 신문사명 매칭

public class OidParse {

	Map<String, Integer> oidMap = new HashMap<>();

	public static void main(String[] args) {

		String oid = "001";
		String aid = "0000000001";
		String url;
		String host = "https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=101";
		String press;
		String title;
		Document doc = null;

		for (int i = 1; i < 645; i++) {
			int oidNum=i;
			
			if (oidNum <10) {				
				oid = "00"+Integer.toString(oidNum);
				System.out.println(oid);
			}else if(oidNum < 100) {
				oid = "0"+Integer.toString(oidNum);
				System.out.println(oid);
			}else {
				oid = Integer.toString(oidNum);
				System.out.println(oid);
			}

			try {
				url = host+"&oid="+oid+"&aid="+aid;
				doc = Jsoup.connect(url).get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Elements elementPress = doc.select("div.article_header");
			Elements elPress = elementPress.select("img");
			press = elPress.attr("title");
			
			Elements elementTitle = doc.select("div.article_info");
			Elements elTitle = elementTitle.select("h3");
			title = elTitle.text();
							
			System.out.println("press : " + press);
			System.out.println("title : " + title);
		}
		
	}
}
