package net.eicnam.fip1.ptt.backend.utils;

import net.eicnam.fip1.sdk.rainbow.authentication.RainbowAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DotEnvReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(DotEnvReader.class);
    private final HashMap<String, String> valuesMap = new HashMap<>();
    private final String path;
    private boolean isParsed = false;

    /**
     * Create a Dotenv Reader
     *
     * @param path The path to the .env file
     */
    public DotEnvReader(final String path)
    {
        this.path = path;
    }

    /**
     * Read the .env file and return list of lines
     *
     * @return The lines in .env file
     */
    public List<String> readFile()
    {
        List<String> fileLines = new ArrayList<>();
        final String fileLocation = (path + "/.env").toLowerCase();
        final Path filePath = Paths.get(fileLocation);

        if (Files.exists(filePath))
        {
            try (final Stream<String> stream = Files.lines(filePath))
            {
                fileLines = stream.collect(Collectors.toList());
            } catch (IOException e)
            {
                LOGGER.error("readFile() > Error reading file: " + e.getMessage());
            }
        }
        return fileLines;
    }

    /**
     * Parse the .env file and store the valid lines
     */
    public void parse()
    {
        for (final String line : readFile())
        {

            final String trimmedLine = line.trim();
            if (isValid(trimmedLine))
            {
                parseLine(trimmedLine);
            }
        }
        isParsed = true;
    }

    /**
     * Parse a line, and store in HashMap if valid
     *
     * @param line The line to check the validity
     */
    public void parseLine(final String line)
    {
        final Pattern pattern = Pattern.compile("^\\s*([A-Za-z0-9_]+)\\s*(=)\\s*(.*)?\\s*$");
        final Matcher matcher = pattern.matcher(line);
        final boolean result = matcher.matches() && matcher.groupCount() >= 3;
        if (result)
        {
            valuesMap.put(matcher.group(1), matcher.group(3));
        }
    }

    /**
     * Test if given string is a valid .env key-value
     *
     * @param str The String to test
     * @return boolean
     */
    public boolean isValid(final String str)
    {
        return !(isWhiteSpace(str) || isComment(str));
    }

    /**
     * Test if given string is whitespace
     *
     * @param str The string to test
     * @return boolean
     */
    private boolean isWhiteSpace(final String str)
    {
        final Pattern p = Pattern.compile("^\\s*$");
        final Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * Test if given string is a comment
     *
     * @param str The string to test
     * @return boolean
     */
    private boolean isComment(final String str)
    {
        return str.startsWith("#") || str.startsWith("////");
    }

    /**
     * Get the value in of .env key
     *
     * @param key The key whose value is wanted
     * @return The String value associated to the key, return null if the key is not found
     */
    public String getValue(final String key)
    {
        return getValue(key, null);
    }

    /**
     * Get the value in of .env key
     *
     * @param key The key whose value is wanted
     * @return The String value associated to the key, return default value if key is not found
     */

    public String getValue(final String key, final String defaultValue)
    {
        if (!isParsed)
        {
            parse();
        }
        return valuesMap.get(key) == null ? defaultValue : valuesMap.get(key);
    }
}
