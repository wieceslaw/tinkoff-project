import ru.tinkoff.edu.java.parser.handler.GitHubLinkHandler;
import ru.tinkoff.edu.java.parser.handler.LinkHandler;
import ru.tinkoff.edu.java.parser.handler.StackOverflowLinkHandler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkHandler linkHandler = new GitHubLinkHandler();
        linkHandler.setNext(new StackOverflowLinkHandler());
        while (true) {
            String link = scanner.nextLine();
            Object data = linkHandler.handleLink(link);
            if (data == null) {
                System.out.println("Unknown link");
            } else {
                System.out.println(data);
            }
        }
    }
}
