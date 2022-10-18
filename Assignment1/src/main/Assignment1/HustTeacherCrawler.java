package Assignment1;

import org.jsoup.nodes.Document;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

public class HustTeacherCrawler implements PageProcessor {

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        List<Selectable> nodes = html.css("div.js_bt a").nodes();

        if(nodes.size() > 0){
            List<String> links = html.css("div.js_bt a").links().all();
            page.addTargetRequests(links);

            page.getResultItems().setSkip(true);
        }else{
            parseTeacherInfo(page);
        }

    }

    private void parseTeacherInfo(Page page){
        Html html = page.getHtml();
        String name = html.css("body > div.mainCont > div.dft-main.clearfix > div.dft-side > div.blockwhite.JS-display > div.js-top.clearfix > div.info > h2", "text").get();
        String sexual = html.css("body > div.mainCont > div.dft-main.clearfix > div.dft-side > div.blockwhite.Psl-info > div.cont > p:nth-child(2)", "text").get();
        String job = html.css("body > div.mainCont > div.dft-main.clearfix > div.dft-side > div.blockwhite.Psl-info > div.cont > p:nth-child(1)", "text").get();
        String direction = html.css("body > div.mainCont > div.dft-main.clearfix > div.dft-content.clearfix > div.dft-rightcont > div.blockwhite.Rsh-focus > div.cont > ul > li > a", "text").get();
        TeacherInfo teacherInfo = new TeacherInfo();
        teacherInfo.setDirection(direction);
        teacherInfo.setJob(job);
        teacherInfo.setName(name);
        teacherInfo.setSexual(sexual);
        page.putField("name",name);
        page.putField("sexual",sexual);
        page.putField("job",job);
        page.putField("direction",direction);
    }


    @Override
    public Site getSite() {
        return Site.me();
    }

    public static void main(String[] args) {
        Spider.create(new HustTeacherCrawler()).addUrl("http://cs.hust.edu.cn/szdw/jsml/axmpyszmlb.htm").thread(50).start();

        /*FilePipeline pipeline = new FilePipeline();
        Spider.create(new HustTeacherCrawler()).addUrl("http://cs.hust.edu.cn/szdw/jsml/axmpyszmlb.htm").addPipeline(new ConsolePipeline())
                .run();*/
    }
}
