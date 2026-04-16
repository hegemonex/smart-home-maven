package com.solvd.smarthome.wordcounter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class WordCounter {

    private static final Logger logger = LogManager.getLogger(WordCounter.class);

    public static void main(String[] args) throws Exception {

        File input = new File("src/main/resources/book.txt");
        File output = new File("src/main/resources/word_count_result.txt");

        String content = FileUtils.readFileToString(input, StandardCharsets.UTF_8);

        Set<String> uniqueWords = Arrays.stream(StringUtils.split(content))
                .map(w -> StringUtils.strip(w, ".,!?;:\"'()-").toLowerCase())
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toSet());

        String result = "Unique word count: " + uniqueWords.size();

        FileUtils.writeStringToFile(output, result, StandardCharsets.UTF_8);

        logger.info("Word count complete. {}", result);
        logger.info("Result written to: {}", output.getAbsolutePath());
    }
}