package iraqedutv.iq;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.Window;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends Activity {
	VideoView videoView;
	ProgressDialog progress;
	private final int ID_MENU_EXIT = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	   super.onCreate(savedInstanceState);
           requestWindowFeature(Window.FEATURE_NO_TITLE);
           setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

           this.setContentView(R.layout.activity_main);
           
           ProgressDialog.show(MainActivity.this, "", "جار التحميل يرجى الانتضار");
           
           videoView = (VideoView)this.findViewById(R.id.videoView1);

           MediaController mc = new MediaController(this);
           videoView.setMediaController(mc);

           videoView.setVideoURI(Uri.parse("rtsp://rtsp://192.168.3.142:554/live/myStream"));
           videoView.start();

           videoView.requestFocus();
     }

    public void run()
        {
          progress.dismiss();
        }






	private long lastPressedTime;
    private static final int PERIOD = 2000;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            switch (event.getAction()) {
            case KeyEvent.ACTION_DOWN:
                if (event.getDownTime() - lastPressedTime < PERIOD) {
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "اضغط على زر العودة مرة ثانية للخروج من التطبيق",
                            Toast.LENGTH_SHORT).show();
                    lastPressedTime = event.getEventTime();
                }
                return true;
            }
        }
        return false;
    }
   
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	//check selected menu item
    	if(item.getItemId() == ID_MENU_EXIT)
    	{
    		//close the Activity
    		this.finish();
    		return true;
    	}
    	return false;
    }   

    @Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
	    //the menu option text is defined in resources
	 
	    //get a SubMenu reference
	    SubMenu sm = menu.addSubMenu("About...");
	    //add menu items to the submenu
	    sm.add("تطبيق قناة التلفزيون التربوي");
	    sm.add("المصمم احمد الحلي");
	    sm.add("Email:ahmedalhily@gmail.com");
	 
	    //it is better to use final variables for IDs than constant values
	    //menu.add(Menu.NONE,1,Menu.NONE,"Exit");
	 
	    //get the MenuItem reference
	    MenuItem item = 
	    	menu.add(Menu.NONE,ID_MENU_EXIT,Menu.NONE,R.string.exitOption);
	    //set the shortcut
	    item.setShortcut('5', 'x');
	 
	    //the menu option text is defined as constant String

	 
	    return true;
	}
    
}
