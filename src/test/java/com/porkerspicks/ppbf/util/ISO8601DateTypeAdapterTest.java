import com.google.gson.JsonPrimitive;
import com.porkerspicks.ppbf.util.ISO8601DateTypeAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ISO8601DateTypeAdapterTest {

    private ISO8601DateTypeAdapter adapter;

    @BeforeEach
    public void setup() {
        adapter = new ISO8601DateTypeAdapter();
    }

    @Test
    public void testSerialize() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(ISO8601DateTypeAdapter.ISO_8601_FORMAT_STRING);
        sdf.setTimeZone(TimeZone.getTimeZone(ISO8601DateTypeAdapter.ISO_8601_TIMEZONE));
        Date date = sdf.parse("2022-01-01T00:00:00.000Z");

        JsonPrimitive json = (JsonPrimitive)adapter.serialize(date, Date.class, null);

        assertEquals("\"2022-01-01T00:00:00.000Z\"", json.toString());
    }

    @Test
    public void testDeserialize() {
        JsonPrimitive json = new JsonPrimitive("2022-01-01T00:00:00.000Z");

        Date date = adapter.deserialize(json, Date.class, null);

        SimpleDateFormat sdf = new SimpleDateFormat(ISO8601DateTypeAdapter.ISO_8601_FORMAT_STRING);
        sdf.setTimeZone(TimeZone.getTimeZone(ISO8601DateTypeAdapter.ISO_8601_TIMEZONE));
        String expected = sdf.format(date);

        assertEquals("2022-01-01T00:00:00.000Z", expected);
    }
}