import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;
import android.os.Handler;
import android.os.Looper;

public class LiveWallpaperService extends WallpaperService {

    @Override
    public Engine onCreateEngine() {
        return new LiveWallpaperEngine();
    }

    private class LiveWallpaperEngine extends Engine {
        private final Handler handler = new Handler(Looper.getMainLooper());
        private SurfaceHolder surfaceHolder;
        private boolean visible;

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            this.surfaceHolder = surfaceHolder;
            // Additional setup if needed
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            this.visible = visible;
            if (visible) {
                draw(); // Trigger initial draw
            }
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            draw(); // Redraw on surface change
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            visible = false; // prevent draws
            handler.removeCallbacksAndMessages(null); // stop any pending tasks
        }

        private void draw() {
            if (!visible) return;
            Canvas canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                // Your drawing code goes here
                // Example: a minimalistic design 
                canvas.drawColor(Color.BLACK);
                // Additional draw calls...
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            // Schedule next drawing
            handler.postDelayed(this::draw, 1000 / 30); // 30 FPS
        }
    }
}