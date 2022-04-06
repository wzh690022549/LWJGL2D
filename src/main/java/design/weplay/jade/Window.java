package design.weplay.jade;

import design.weplay.constant.Constants;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * GLFW窗口类
 *
 * @author WangZiHao
 */
public class Window {
    /** 宽 */
    private int width;
    /** 高 */
    private int height;
    /** 标题 */
    private String title;
    /** 窗口内存地址 */
    private long glfwWindow;

    private Window() {
        this.width = Constants.WINDOW_WIDTH;
        this.height = Constants.WINDOW_HEIGHT;
        this.title = Constants.WINDOW_TITLE;
    }

    private static final class WindowHolder {
        /** 窗口实例 */
        private static final Window INSTANCE = new Window();
    }

    public static Window getInstance() {
        return WindowHolder.INSTANCE;
    }

    public void run() {
        System.out.println("LWJGL 版本: " + Version.getVersion() + " !");

        // 初始化窗口
        init();
        // 窗口主循环
        loop();
    }

    /**
     * 初始化窗口
     */
    private void init() {
        // 设置GLFW错误回调, 将GLFW错误信息输出到控制台
        GLFWErrorCallback.createPrint(System.err).set();

        // 初始化GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("无法初始化GLFW");
        }

        // 配置GLFW
        glfwDefaultWindowHints();
        // 先设置窗口不可见
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        // 窗口大小可调整
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        // 最大化窗口
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // 创建窗口
        glfwWindow = glfwCreateWindow(width, height, title, NULL, NULL);
        if (glfwWindow == NULL) {
            throw new IllegalStateException("创建GLFW窗口失败");
        }

        // 使用OpenGL上下文
        glfwMakeContextCurrent(glfwWindow);
        // 开启垂直同步
        glfwSwapInterval(1);

        // 最后设置窗口可见
        glfwShowWindow(glfwWindow);

        // 创建OpenGL上下文
        GL.createCapabilities();
    }

    /**
     * 窗口主循环
     */
    private void loop() {
        while (!glfwWindowShouldClose(glfwWindow)) {
            // 轮询事件
            glfwPollEvents();

            // 设置颜色缓存的清除值 (背景颜色)
            glClearColor(1,1,1,1);
            // 将缓存清除为预先的设置值 (glClearColor设置的颜色) GL_COLOR_BUFFER_BIT 指定当前被激活为写操作的颜色缓存
            glClear(GL_COLOR_BUFFER_BIT);

            // 交换缓冲区
            glfwSwapBuffers(glfwWindow);
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
