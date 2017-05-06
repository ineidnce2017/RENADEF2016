package gob.inei.renadef2016.utilities;

import gob.inei.renadef2016.R;
import gob.inei.renadef2016.fragments.C3CAP100_Fragment_101_A;
import gob.inei.dnce.components.DialogComponent;
import gob.inei.dnce.components.LabelComponent;
import gob.inei.dnce.interfaces.Respondible;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class ClickPopup001 implements View.OnClickListener, Respondible {
	private Context context;
	private String textKit;
	private int idDes;
	private DialogComponent dc;

	public ClickPopup001(Context context, String textKit, int idDes) {
		this.context = context;
		this.textKit = textKit;
		this.idDes = idDes;
	}

	@Override
	public void onClick(View v) {
		dc = new DialogComponent(
				context, this,
				DialogComponent.TIPO_DIALOGO.NEUTRAL, textKit,
				"",
				DialogComponent.TIPO_ICONO.INFO);
		dc.setView(createLayout(this.idDes));
		dc.showDialog();
	}

	@Override
	public void onCancel() {
	}

	@Override
	public void onAccept() {
	}

	private View createLayout(int idDes) {
		LinearLayout ly = new LinearLayout(
				context);
		ly.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		ly.setBackgroundResource(R.color.blanco);
		ly.setOrientation(LinearLayout.VERTICAL);
		LabelComponent lblDesc = new LabelComponent(
				context)
				.size(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT).
				text(idDes).textSize(16)
				.negrita();
		ly.addView(lblDesc);
		return ly;
	}
}
