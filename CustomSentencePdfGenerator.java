/*

Extra libraries used -
1.itextpdf package
2.Jsoup package

*/
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.jsoup.Jsoup;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.Locale;
import java.util.Scanner;

public class CustomSentencePdfGenerator {
    public static void main(String[] args) throws IOException {
        org.jsoup.nodes.Document doc = Jsoup.connect("https://www.javatpoint.com/").get();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a word");
        String word = sc.next();
        String keyWordConcat = ":containsOwn("+word+")";

        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);

        String content = doc.select(keyWordConcat).text();

        iterator.setText(content);
        int index = 0;

        while(iterator.next()!=BreakIterator.DONE){
            String sentence = content.substring(index,iterator.current());
            System.out.println("Sentence: "+sentence);
            index = iterator.current();
        }

        Document document = new Document();
        try{
            PdfWriter.getInstance(document,new FileOutputStream("SentenceExample.pdf"));
            document.open();
            Paragraph p1 = new Paragraph("Hello! Welcome to sentence generator with java");

            System.out.println("The word is "+word);
            Paragraph p2 = new Paragraph("The first sentence is \n ***"+content);
            document.add(p1);
            document.add(p2);

        }catch (Exception e){
            System.out.println(e);
        }
        document.close();


    }
}
