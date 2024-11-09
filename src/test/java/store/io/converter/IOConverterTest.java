package store.io.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.model.dto.PreOrderDTO;

public class IOConverterTest {

    @Test
    @DisplayName("")
    void test() {
        // given
        String source = "[콜라-10],[오렌지주스-10]";
        // when
        List<PreOrderDTO> actual = IOConverter.toPreOrderDTOListFrom(source);
        // then
        assertThat(actual.size()).isEqualTo(2);
    }

}

