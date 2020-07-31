import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TechMojo {

  public static void main(String[] args) {
    HashTags ht = new HashTags();
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    sc.nextLine();
    while (n-- > 0) {
      ht.updateHashTags(sc.nextLine());
    }
    // print top 10 trending hashtags
    ht.printTrendingHashTags(10);
  }
}

class HashTags {

  private Map<String, Integer> hashTags;

  public HashTags() {
    this.hashTags = new HashMap<>();
  }

  public void updateHashTags(String tweet) {
    int hashIndex = tweet.indexOf('#');
    if (hashIndex != -1) {
      String[] hts = tweet.substring(hashIndex + 1).split("#");
      for (String ht : hts) {
        ht = ht.split(" ")[0];
        ht = "#" + ht.trim().toLowerCase();
        Integer count = 1;
        if (this.hashTags.containsKey(ht)) {
          count = this.hashTags.get(ht) + 1;
        }
        this.hashTags.put(ht, count);
      }
    }
  }

  public List<String> getTrendingHashTags(int trendingCount) {
    return this.hashTags.entrySet().stream()
        .sorted((e1, e2) -> e2.getValue() - e1.getValue())
        .limit(trendingCount)
        .map(e -> e.getKey())
        .collect(Collectors.toList());
  }

  public void printTrendingHashTags(int trendingCount) {
    System.out.println("\n=================");
    System.out.println("Trending HashTags");
    System.out.println("=================");
    List<String> trendingHashTags = this.getTrendingHashTags(trendingCount);
    IntStream.range(0, trendingHashTags.size())
        .forEach(
            i ->
                System.out.println(
                    String.format(
                        "%d. %s\t==> count(%d)",
                        (i + 1),
                        trendingHashTags.get(i),
                        this.hashTags.get(trendingHashTags.get(i)))));
  }
}
