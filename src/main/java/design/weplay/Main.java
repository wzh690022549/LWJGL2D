package design.weplay;

import design.weplay.jade.Window;

/**
 * 主类
 * 
 * @author WangZiHao
 */
public class Main {
    public static void main(String[] args) {
        // 获取窗口实例
        Window window = Window.getInstance();
        // 运行窗口
        window.run();
    }
}
