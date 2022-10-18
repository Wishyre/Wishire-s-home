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

public class WhuTeacherCrawler implements PageProcessor {

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        //List<Selectable> nodes = html.css("body > div.idnex-wrapper > div.content_ny > div.right_list > div.right_list_sp > div:nth-child(2) > div > table > tbody > tr:nth-child(2) > td.w1").nodes();

        List<Selectable> nodes = html.css("body > div.idnex-wrapper > div.content_ny > div.right_list > div.right_list_sp > div.sp2.teacher_zc_t > div").nodes();

        if(nodes.size() > 0){
            List<String> links = html.css("body > div.idnex-wrapper > div.content_ny > div.right_list > div.right_list_sp > div.sp2.teacher_zc_t > div").links().all();
            page.addTargetRequests(links);
            page.getResultItems().setSkip(true);
        }else{
            parseTeacherInfo(page);
        }

        parseTeacherInfo(page);

    }

    private void parseTeacherInfo(Page page){
        Html html = page.getHtml();
        List<String> names = html.css("body > div.idnex-wrapper > div.content_ny > div.right_list > div.right_list_sp > div:nth-child(2) > div > table > tbody > tr > td.w1 a","text").all();
//        String sexual = html.css("body > div.idnex-wrapper > div.content_ny > div.right_list > div.right_list_sp > div:nth-child(2) > div > table > tbody > tr:nth-child(2) > td.w2", "text").get();
//        String job = html.css("teacher_zc_list w4", "text").get();
//        String direction = html.css("teacher_zc_list w5", "text").get();
        List<String> job = html.css("body > div.idnex-wrapper > div.content_ny > div.right_list > div.right_list_sp > div:nth-child(2) > div > table > tbody > tr > td.w4","text").all();
        List<String> sexual = html.css("body > div.idnex-wrapper > div.content_ny > div.right_list > div.right_list_sp > div:nth-child(2) > div > table > tbody > tr > td.w2","text").all();
        List<String> direction = html.css("body > div.idnex-wrapper > div.content_ny > div.right_list > div.right_list_sp > div:nth-child(2) > div > table > tbody > tr > td.w5","text").all();
        //TeacherInfo teacherInfo = new TeacherInfo();
        //teacherInfo.setDirection(direction);
        //teacherInfo.setJob(job);
        //teacherInfo.setName(name);
       // teacherInfo.setSexual(sexual);\
        //System.out.println("sexual "+sexual.size());
        //System.out.println("name: "+names.size());
        for(int i=0;i< names.size();i++){
            //System.out.println("Professor Num: "+(i+1));
            // page.putField("names",names.get(i));
            //page.putField("size",names.size());
            //page.putField("sexual",sexual);
            System.out.println("Name: "+names.get(i));
            System.out.println("Sexual: "+sexual.get(i));
            System.out.println("Job: "+job.get(i));
            System.out.println("Direction: "+direction.get(i));
            System.out.println("");
        }
        //System.out.println("get page "+names.size());

       // page.putField("direction",direction);
    }


    @Override
    public Site getSite() {
        return Site.me();
    }

    public static void main(String[] args) {
        Spider.create(new WhuTeacherCrawler()).addUrl("http://cs.whu.edu.cn/teacher.aspx?showtype=jobtitle&typename=%e6%95%99%e6%8e%88").start();

        /*FilePipeline pipeline = new FilePipeline();
        Spider.create(new HustTeacherCrawler()).addUrl("http://cs.hust.edu.cn/szdw/jsml/axmpyszmlb.htm").addPipeline(new ConsolePipeline())
                .run();*/
    }
}
