package Assignment1;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

public class MITTeacherCrawler implements PageProcessor {

    @Override
    public void process(Page page) {
        //System.out.println("successeeee");
        Html html = page.getHtml();
        //System.out.println("successeeee");
        List<Selectable> nodes = html.css("#pjax-content > div > div.news-list-table > div > table > tbody").nodes();
        //List<Selectable> nodes = (List<Selectable>) html;

        //System.out.println(nodes.size());
        if(nodes.size() > 0){
            List<String> links = html.css("#pjax-content > div > div.news-list-table > div > table > tbody").links().all();
            page.addTargetRequests(links);
            //System.out.println("success");
            page.getResultItems().setSkip(true);
        }else{
            parseTeacherInfo(page);
            //System.out.println("failed");
        }

    }

    private void parseTeacherInfo(Page page){

        Html html = page.getHtml();
        String name = html.css("body > div.wrap > div.content > div > div.introduce-form.row > div.form.col-md-7 > p:nth-child(1) > span:nth-child(2)", "text").get();
        String sexual = html.css("body > div.wrap > div.content > div > div.introduce-form.row > div.form.col-md-7 > p:nth-child(2) > span:nth-child(2)", "text").get();
        String job = html.css("body > div.wrap > div.content > div > div.introduce-form.row > div.form.col-md-7 > p:nth-child(5) > span:nth-child(2)", "text").get();
        String direction = html.css("body > div.wrap > div.content > div > div.introduce-form.row > div.form.col-md-7 > p:nth-child(10) > span:nth-child(2)", "text").get();
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
       // System.out.println("successssss");
        Spider.create(new MITTeacherCrawler()).addUrl("https://cc.nankai.edu.cn/13250/list.htm").thread(20).start();
        Spider.create(new MITTeacherCrawler()).addUrl("https://cc.nankai.edu.cn/fjswfyjy/list.htm").thread(20).start();
        Spider.create(new MITTeacherCrawler()).addUrl("https://cc.nankai.edu.cn/js/list.htm").thread(20).start();
        Spider.create(new MITTeacherCrawler()).addUrl("https://cc.nankai.edu.cn/syjxdw/list.htm").thread(20).start();
       // System.out.println("successssss");
        /*FilePipeline pipeline = new FilePipeline();
        Spider.create(new HustTeacherCrawler()).addUrl("http://cs.hust.edu.cn/szdw/jsml/axmpyszmlb.htm").addPipeline(new ConsolePipeline())
                .run();*/
    }
}
