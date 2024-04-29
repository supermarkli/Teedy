package com.sismics.docs.core.util;

import junit.framework.TestCase;
import org.junit.Test;

import java.nio.file.Path;

public class DirectoryUtilTest extends TestCase {

    @Test
    public void testGetLogDirectory() {
        Path logDir = DirectoryUtil.getLogDirectory();
        assertNotNull("Log directory should not be null", logDir);
        // 你还可以添加更多的断言来验证路径是否符合预期
    }
}
