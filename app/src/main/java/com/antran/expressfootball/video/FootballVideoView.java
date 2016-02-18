package com.antran.expressfootball.video;

import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.antran.expressfootball.R;

import java.io.IOException;

/**
 * Created by AnTran on 12/12/2015.
 */
public class FootballVideoView {

    private View rootView;
//    private VideoView video;
    private TextureView textureView;
    private MediaController mediaController;
    private MediaPlayer mediaPlayer;

    public FootballVideoView(final View rootView, final String videoLink) {
        this.rootView = rootView;
//        video = (VideoView) rootView.findViewById(R.id.video);
//
//        if (mediaController == null)
//            mediaController = new MediaController(rootView.getContext());
//
//        mediaController.setAnchorView(video);
//        mediaController.setMediaPlayer(video);
//        video.setMediaController(mediaController);
//        video.setVideoPath(videoLink);

        textureView = (TextureView) rootView.findViewById(R.id.texture_view);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Surface s = new Surface(surface);

                try {
                    mediaPlayer= new MediaPlayer();
                    mediaPlayer.setDataSource(videoLink);
                    mediaPlayer.setSurface(s);
                    mediaPlayer.prepare();
                    mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                        @Override
                        public void onBufferingUpdate(MediaPlayer mp, int percent) {

                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {

                        }
                    });
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {

                        }
                    });
                    mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                        @Override
                        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

                        }
                    });
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.start();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });

    }

    public void setListener(FootballVideoViewListener ltn) {
    }

    public void setLink(String videoLink) {
//        video.setVideoPath(videoLink);
    }
}
