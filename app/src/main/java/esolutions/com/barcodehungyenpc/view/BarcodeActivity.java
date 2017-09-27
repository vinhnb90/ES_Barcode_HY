package esolutions.com.barcodehungyenpc.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.Result;

import esolutions.com.barcodehungyenpc.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class BarcodeActivity extends BaseActivity implements ZXingScannerView.ResultHandler {

    private static final String TAG = BarcodeActivity.class.getName();
    public static final int RESULT_CODE = 888;
    public static final int REQUEST_CODE = 889;
    public static final String PARAM_BARCODE = "PARAM_BARCODE";
    private ZXingScannerView mScannerView;

    Button btnCancelTextBarcode;
    Button btnOKTextBarcode;
    TextView tvTextBarcode;
    LinearLayout llBarcode, llMainBarcode;
    private boolean isOKText = false;
    private String textBarcode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            initView();
            handleListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v(TAG, rawResult.getText()); // Prints scan results
        Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        tvTextBarcode.setText(rawResult.getText());
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as DataTable1 handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    protected void initView() {


//        LayoutInflater inflater = this.getLayoutInflater();
//        View view = inflater.inflate(R.layout.dialog_barcode, null);
//        llBarcode = (LinearLayout) view.findViewById(R.id.ll_dialog_barcode);
//        llMainBarcode = (LinearLayout) view.findViewById(R.id.ll_dialog_barcode_main);
//        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
//
//        llBarcode.removeView(llMainBarcode);
//        llBarcode.addView(mScannerView);


        setContentView(R.layout.activity_barcode);
        super.hideBar();
        mScannerView = (ZXingScannerView)findViewById(R.id.zxingView);
        String deviceMan = android.os.Build.MANUFACTURER;
        if (deviceMan.equalsIgnoreCase("HUAWEI")) {
            mScannerView.setAspectTolerance(0.5f);
        }
        btnCancelTextBarcode = (Button) findViewById(R.id.btn_dialog_barcode_cancel);
        btnOKTextBarcode = (Button) findViewById(R.id.btn_dialog_barcode_ok);
        tvTextBarcode = (TextView) findViewById(R.id.tv_dialog_barcode_text);

        mScannerView.setResultHandler(BarcodeActivity.this);
        mScannerView.startCamera();

    }

    @Override
    protected void handleListener() throws Exception {
        btnCancelTextBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mScannerView == null || tvTextBarcode == null)
                    return;

                tvTextBarcode.setText("");
                mScannerView.setResultHandler(BarcodeActivity.this); // Register ourselves as a handler for scan results.
                mScannerView.startCamera();
                mScannerView.resumeCameraPreview(BarcodeActivity.this);
            }
        });
        btnOKTextBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvTextBarcode == null)
                    return;
                isOKText = true;
                textBarcode = tvTextBarcode.getText().toString().trim();

                Intent intent = new Intent();
                intent.putExtra(PARAM_BARCODE, textBarcode);
                setResult(BarcodeActivity.RESULT_CODE, intent);
                finish();
            }
        });
    }

    @Override
    protected void setAction(Bundle savedInstanceState) throws Exception {

    }

}
