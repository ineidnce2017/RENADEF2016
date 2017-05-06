package gob.inei.renadef2016.utilities;

import gob.inei.renadef2016.R;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Validador {
	
	private static final String CLASS_TAG = "Validador";
	
	
	//************Campos requeridos**************
	
	//EDITTEXT
	public static boolean requeridoEdittext(EditText editText, String nombreCampo) {
		Log.d(CLASS_TAG, "requeridoEdittext()");

		boolean valido = true;		

		String text = editText.getText().toString().trim();
		String mensaje = nombreCampo + " requerido";		

		if (text.length() == 0) {
			//editText.setText(text);
			//editText.setError(nombreCampo + " requerido");
			ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
			SpannableStringBuilder ssbuilder = new SpannableStringBuilder(mensaje);
			ssbuilder.setSpan(fgcspan, 0, mensaje.length(), 0);
			editText.setFocusable(true);
			editText.setFocusableInTouchMode(true);
			editText.requestFocus();
			editText.setBackgroundResource(R.color.verdeagua);
			editText.setError(ssbuilder);
			//editText.setError(mensaje);			
			valido = false;
		}
		else
		{
			editText.setBackgroundResource(R.color.cajaweb);
			editText.setError(null);
		}

		return valido;
	}
	
	//EDITTEXT
	public static boolean noRequeridoEdittext(EditText editText, String nombreCampo) {
		Log.d(CLASS_TAG, "noRequeridoEdittext()");

		boolean valido = true;

		String text = editText.getText().toString().trim();
		String mensaje = nombreCampo + " no corresponde";		

		if (text.length() > 0) {
			ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
			SpannableStringBuilder ssbuilder = new SpannableStringBuilder(mensaje);
			ssbuilder.setSpan(fgcspan, 0, mensaje.length(), 0);
			editText.setFocusable(true);
			editText.setFocusableInTouchMode(true);
			editText.requestFocus();
			editText.setBackgroundResource(R.color.verdeagua);
			editText.setError(ssbuilder);			
			valido = false;
		}
		else
		{
			editText.setBackgroundResource(R.color.cajaweb);
			editText.setError(null);
		}

		return valido;
	}
	
	//SPINNER
	public static boolean requeridoSpinner(Context context, Spinner spinner, String nombreCampo) {
		Log.d(CLASS_TAG, "requeridoSpinner()");

		boolean valido = true;
		String mensaje = nombreCampo + " requerido";

		if (spinner.getSelectedItemPosition() == 0) {
			/*spinner.setFocusable(true);
			spinner.setFocusableInTouchMode(true);
			spinner.requestFocus();*/
			/*Toast.makeText(context, "Seleccione una opción para " + nombreCampo, Toast.LENGTH_SHORT).show();*/
			
			/*new AlertDialog.Builder(context)
		    .setTitle("Error")
		    .setMessage("Seleccione una opción para " + nombreCampo)
		    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		        	
		        }
		     })		    
		     .show();*/
			
			spinner.setFocusable(true);
			spinner.setFocusableInTouchMode(true);
			spinner.requestFocus();
			spinner.setBackgroundResource(R.color.verdeagua);
			//Toast.makeText(context, spinner.getAdapter().getView(1, null, null).getClass().toString(),Toast.LENGTH_SHORT).show();
			//Toast.makeText(context,((TextView)spinner.getAdapter().getView(1, null, null)).getText().toString(),Toast.LENGTH_SHORT).show();
			//ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
			//SpannableStringBuilder ssbuilder = new SpannableStringBuilder("jojolete");
			//ssbuilder.setSpan(fgcspan, 0, "jojolete".length(), 0);
			//((TextView)spinner.getAdapter().getView(0, null, null)).setTextColor(Color.RED);
			//((TextView)spinner.getAdapter().getView(0, null, null)).setError(ssbuilder);
			//spinner.performClick();
			//crearDialogoValidacion(mensaje,context).show();
			valido = false;	
		}
		else
		{
			spinner.setBackgroundResource(R.color.cajaweb);		
		}

		return valido;
	}
	
	//SPINNER
	public static boolean noRequeridoSpinner(Context context, Spinner spinner, String nombreCampo) {
		Log.d(CLASS_TAG, "requeridoSpinner()");

		boolean valido = true;

		if (spinner.getSelectedItemPosition() > 0) {
			spinner.setFocusable(true);
			spinner.setFocusableInTouchMode(true);
			spinner.requestFocus();
			spinner.setBackgroundResource(R.color.verdeagua);
			//spinner.performClick();
			valido = false;	
		}
		else
		{
			spinner.setBackgroundResource(R.color.cajaweb);		
		}

		return valido;
	}
	
	//TEXTVIEW
	public static boolean requeridoTextview(TextView textview, String nombreCampo) {
		Log.d(CLASS_TAG, "requeridoTextview()");

		boolean valido = true;

		String textviewvalue = textview.getText().toString().trim();
		String mensaje = nombreCampo + " requerido";		

		if (textviewvalue.length() == 0) {
			ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
			SpannableStringBuilder ssbuilder = new SpannableStringBuilder(mensaje);
			ssbuilder.setSpan(fgcspan, 0, mensaje.length(), 0);
			textview.setError(ssbuilder);
			textview.setFocusable(true);
			textview.setFocusableInTouchMode(true);
			textview.requestFocus();
			valido = false;
		}
		else
		{
			textview.setError(null);
		}

		return valido;
	}
	
	
	public static void errorEditText(EditText editText, String mensajeError){
		editText.clearFocus();
		ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
		SpannableStringBuilder ssbuilder = new SpannableStringBuilder(mensajeError);
		ssbuilder.setSpan(fgcspan, 0, mensajeError.length(), 0);
		editText.setFocusable(true);
		editText.setFocusableInTouchMode(true);
		editText.requestFocus();
		//editText.setBackgroundResource(R.color.verdeagua);
		editText.setError(ssbuilder);				
	}
	
//	public static void errorCenacomSpinner(Context ctx,CenacomSpinner spinnerSombrear, CenacomSpinner spinnerDesombrear, String mensajeError){
//		spinnerSombrear.clearFocus();
//		ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
//		SpannableStringBuilder ssbuilder = new SpannableStringBuilder(mensajeError);
//		ssbuilder.setSpan(fgcspan, 0, mensajeError.length(), 0);
//		spinnerSombrear.setFocusable(true);
//		spinnerSombrear.setFocusableInTouchMode(true);
//		spinnerSombrear.requestFocus();
//		spinnerSombrear.setBackgroundResource(R.color.verdeagua);
//		
//		if (spinnerDesombrear != null){
//			spinnerDesombrear.setBackgroundResource(R.color.cajaweb);
//		}
//		Toast.makeText(ctx, mensajeError, Toast.LENGTH_SHORT).show();				
//	}
	
//	public static void errorCenacomCheckBox(Context ctx,CenacomCheckBox checkBox, String mensajeError){
//		checkBox.clearFocus();
//		ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
//		SpannableStringBuilder ssbuilder = new SpannableStringBuilder(mensajeError);
//		ssbuilder.setSpan(fgcspan, 0, mensajeError.length(), 0);
//		checkBox.setFocusable(true);
//		checkBox.setFocusableInTouchMode(true);
//		checkBox.requestFocus();
//		checkBox.setBackgroundResource(R.color.verdeagua);
//		
//		if (checkBox != null){
//			checkBox.setBackgroundResource(R.color.cajaweb);
//		}
//		Toast.makeText(ctx, mensajeError, Toast.LENGTH_SHORT).show();				
//	}
}
