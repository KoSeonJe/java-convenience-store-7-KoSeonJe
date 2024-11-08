package store;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileLoaderTest {

    private final FileLoader fileloader = new FileLoader();

    @DisplayName("파일 경로를 입력하면, 해당 경로 파일의 모든 데이터를 라인을 단위로 리스트를 반한다.")
    @Test
    void uploadFile() {
      // given
        String fileName = "products.md";
      // when
        List<String> rawFileData = fileloader.loadFile(fileName);
      // then
        assertThat(rawFileData).isNotEmpty();
        assertThat(rawFileData.getFirst()).isEqualTo("콜라,1000,10,탄산2+1");
    }
}
