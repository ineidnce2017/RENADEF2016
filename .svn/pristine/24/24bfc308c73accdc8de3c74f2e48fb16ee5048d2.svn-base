package gob.inei.renadef2016.listener;

import gob.inei.renadef2016.activity.CuestionarioFragmentActivity;
import gob.inei.renadef2016.activity.ExportacionFragmentActivity;
import gob.inei.renadef2016.activity.ReportesFragmentActivity;
import gob.inei.dnce.components.MasterActivity;
import gob.inei.dnce.listeners.NavigationClickListener;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MyNavigationClickListener extends NavigationClickListener {
	
	public MyNavigationClickListener(MasterActivity activity, int opcionId,
			ListView drawerList, DrawerLayout drawerLayout) {
		super(activity, opcionId, drawerList, drawerLayout);
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Intent i = null;
		switch (position) {
		case 0:				
//			getActivity().nextFragment(0);
			i = new Intent(getActivity(), CuestionarioFragmentActivity.class);	
			break;
		case 1:
			i = new Intent(getActivity(), ReportesFragmentActivity.class);		
			break;
		case 2:
			i = new Intent(getActivity(), ExportacionFragmentActivity.class);
			break;		
		case 3:
			getActivity().uploadData();
			break;		
		case 4:
			getActivity().uploadMarco();
			break;	
		default:
			i = null;
			break;
		}		
		if (i == null) {
			return;
		}
		
		getDrawerList().setItemChecked(position, true);
		getDrawerLayout().closeDrawer(getDrawerList());
		if (position != getOpcionId()) {
			getActivity().startActivity(i);		
			getActivity().finish();
		} else {
			getActivity().nextFragment(0);
		}
	}
	
}
