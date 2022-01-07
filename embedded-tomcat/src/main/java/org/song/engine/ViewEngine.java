package org.song.engine;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.loader.ServletLoader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class ViewEngine {
    private PebbleEngine engine;

    public ViewEngine(ServletContext context) {
        ServletLoader loader = new ServletLoader(context);
        loader.setCharset("utf-8");
        loader.setPrefix("/WEB-INF/templates");
        loader.setSuffix(".html");
        engine = new PebbleEngine.Builder()
                .autoEscaping(true)
                .cacheActive(false)
                .loader(loader)
                .build();
    }

    public void render(String view, Writer writer, Map<String, Object> model) throws PebbleException, IOException {
        PebbleTemplate template = engine.getTemplate(view);
        template.evaluate(writer, model);
    }
}
