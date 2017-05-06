package gob.inei.renadef2016.utilities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class TaskExecute<T, E, V> extends AsyncTask<T, E, V> {
	private ProgressDialog pDialog;	
	private Object tipo;
	private Object result;
	private boolean progress;
	private String callback; 
	private Object[] pObject;
	private Class<?>[] pClass;
	
	public TaskExecute(Object tipo){
		this(tipo, false);
	}
	
	public TaskExecute(Object tipo, boolean hasProgress){
		this.tipo = tipo;
		this.progress = hasProgress;
	}
	
	public void callback(String callback, Object... params){
		this.callback = callback;
		if(params.length>0){
			this.pObject = params;
			this.pClass = new Class<?>[params.length];
			for(int x=0;x<params.length;x++){
				this.pClass[x] = params[x].getClass();
			}
		}
	}
	
	private V invocateReflection(){
		if(callback != null){
			try {
				for(Method m : tipo.getClass().getDeclaredMethods()){
					Log.e("obssss", "obsss: "+m.getName());
				}
				Log.e("mmmmm", "mmmmm: "+pClass.getClass());
				Log.e("nnnnn", "nnnnn: "+pObject.getClass());
				Method metodo = tipo.getClass().getDeclaredMethod(callback, pClass);
				Log.e("llegas", "llegas: llegas");
				result = metodo.invoke(tipo, pObject);
				if(result == Boolean.class)
					return (V)result;
			} catch (NoSuchMethodException e) {
				Log.e("TaskExecute", "NoSuchMethodException: "+e.getMessage());
			} catch (IllegalArgumentException e) {
				Log.e("TaskExecute", "IllegalArgumentException: "+e.getMessage());
			} catch (IllegalAccessException e) {
				Log.e("TaskExecute", "IllegalAccessException: "+e.getMessage());
			} catch (InvocationTargetException e) {
				Log.e("TaskExecute", "InvocationTargetException: "+e.getMessage());
			}
		}
		return null;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if(progress){
			pDialog = new ProgressDialog((Activity)tipo);
			pDialog.setMessage("Iniciando aplicación. Por favor espere...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
	}
	
	@Override
	protected V doInBackground(T... params) {
		// TODO Auto-generated method stub
		return invocateReflection();
	}
	
	@Override
	protected void onProgressUpdate(E... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}
	
	@Override
	protected void onPostExecute(V result) {
		super.onPostExecute(result);
		if(progress){
			pDialog.dismiss();
		}
		Log.e("result ", "result: "+(V)result);
//		this.activity.runOnUiThread(new Runnable() {
//			public void run() {
//				DialogComponent dlg = new DialogComponent(activity, Inicializacion.this, 
//						DialogComponent.TIPO_DIALOGO.NEUTRAL, titulo, msg);
//				dlg.showDialog();				
//			}
//		});
	}
	
	public TaskExecute<T, E, V> addCallback(String callback, Object... params) {this.callback(callback, params); return this;}

}
