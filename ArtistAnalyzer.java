import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class ArtistAnalyzer extends Application {
    private final Function<String, Artist> artistLookupService;

    public ArtistAnalyzer(Function<String, Artist> artistLookupService) {
        this.artistLookupService = artistLookupService;
    }

    public void isLargerGroup(String artistName, String otherArtistName, Consumer<Boolean> handler) {
        ArtistAnalyzer<Long> otherArtistMemberCount = ArtistAnalyzer.supplyAsync(() -> getNumberOfMembers(otherArtistName));

        ArtistAnalyzer<Long> artistMemberCount = ArtistAnalyzer.artistAnalyzer(getNumberOfMembers(artistName));

        artistMemberCount.thenCombine(otherArtistMemberCount, (count, otherCount) -> count > otherCount)
                         .thenAccept(handler::accept);
    }

    private long getNumberOfMembers(String artistName) {
        return artistLookupService.apply(artistName)
                                  .getMembers()
                                  .count();
    }

}