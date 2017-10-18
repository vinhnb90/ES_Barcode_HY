package esolutions.com.barcodehungyenpc.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import esolutions.com.barcodehungyenpc.R;
import esolutions.com.barcodehungyenpc.entity.CongToGuiKDProxy;
import esolutions.com.barcodehungyenpc.entity.CongToPBProxy;
import esolutions.com.barcodehungyenpc.utils.Common;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class DsCongToAdapter extends RecyclerView.Adapter<DsCongToAdapter.DsCtoViewHolder> {
    private Context mContext;
    private List<CongToGuiKDProxy> mListKD;
    private List<CongToPBProxy> mListPB;
    private Common.KIEU_CHUONG_TRINH mKieuChuongTrinh;
    private OnDsCtoAdapterIteraction mListener;
    private static Drawable sDrawableUnGhim, sDrawableGhim, sDrawableChon, sDrawableUnChon;
    private static int sWhiteColor, sLightColor, sBlackColor;
    private Common.MENU_BOTTOM_KD menuBottomKD;

    //history
    private Common.TYPE_SESSION mTypeSessionHistory;
    private String mDateSessionHistory = "";
    private boolean isHistoryAdapter;
    //phân biệt adapter đang sử dụng các công tơ được ghim = true
    // hoặc đang sử dụng tất cả các công tơ = false
//    private boolean isDsCtoGhim = false;

    public DsCongToAdapter(Context mContext, List<CongToGuiKDProxy> mListKD,
                           List<CongToPBProxy> mListPB, Common.KIEU_CHUONG_TRINH kieuChuongTrinh
    ) throws Exception {


        this.mContext = mContext;
        this.mListKD = new ArrayList<>();
        this.mListKD.addAll(mListKD);

        this.mListPB = new ArrayList<>();
        this.mListPB.addAll(mListPB);

        this.mKieuChuongTrinh = kieuChuongTrinh;

        if (sDrawableUnGhim == null) {
            sDrawableUnGhim = ContextCompat.getDrawable(mContext, R.mipmap.ic_un_ghim);
        }
        if (sDrawableGhim == null) {
            sDrawableGhim = ContextCompat.getDrawable(mContext, R.mipmap.ic_ghim);
        }
        if (sDrawableUnChon == null) {
            sDrawableUnChon = ContextCompat.getDrawable(mContext, R.mipmap.ic_un_chon);
        }
        if (sDrawableChon == null) {
            sDrawableChon = ContextCompat.getDrawable(mContext, R.mipmap.ic_chon);
        }

        sWhiteColor = ContextCompat.getColor(mContext.getApplicationContext(), R.color.white);
        sLightColor = ContextCompat.getColor(mContext.getApplicationContext(), R.color.primary_light);
        sBlackColor = ContextCompat.getColor(mContext.getApplicationContext(), R.color.black_less);

//        this.isDsCtoGhim = isDsCtoGhim;

        if (mContext instanceof OnDsCtoAdapterIteraction) {
            mListener = (OnDsCtoAdapterIteraction) mContext;
        } else {
            throw new Exception("class must be implement OnDsCtoAdapterIteraction!");
        }
    }

    public void setMenuBottom(Common.MENU_BOTTOM_KD menuBottomKD) {
        this.menuBottomKD = menuBottomKD;
    }

    public void setHistoryAdapter(boolean historyAdapter, Common.TYPE_SESSION typeSessionHistory, String dateSessionHistory) {
        isHistoryAdapter = historyAdapter;
        mTypeSessionHistory = typeSessionHistory;
        mDateSessionHistory = dateSessionHistory;
        notifyDataSetChanged();
    }

    @Override
    public DsCtoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.row_rv_ds_cto, parent, false);
        return new DsCtoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DsCtoViewHolder holder, int position) {
        Common.runAnimationClickView(holder.relativeLayout, R.anim.twinking_view, Common.TIME_DELAY_ANIM);

        holder.tvSTT.setText(position + 1 + "");

        String SO_CTO = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListKD.get(position).getSO_CTO() : mListPB.get(position).getSO_CTO();
        holder.tvSoCto.setText(SO_CTO);

        String MA_CTO = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListKD.get(position).getMA_CTO() : mListPB.get(position).getMA_CTO();
        holder.tvMaCto.setText(MA_CTO);

        String MA_CLOAI = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListKD.get(position).getMA_CLOAI() : mListPB.get(position).getMA_CLOAI();
        holder.tvMaCLoai.setText(MA_CLOAI);

        if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) {
            holder.tvTitleSoIDBBan.setVisibility(View.GONE);
            holder.tvSoBBanKD.setVisibility(View.GONE);

            holder.tvTitleIdBBanTamThoi.setVisibility(View.VISIBLE);
            holder.tvIdBBanTamThoi.setVisibility(View.VISIBLE);

            String SO_GKDCT_MTB = mListKD.get(position).getSO_GKDCT_MTB();
            holder.tvTitleIdBBanTamThoi.setText("Số biên bản gửi kiểm định tạm thời: ");
            holder.tvIdBBanTamThoi.setText(SO_GKDCT_MTB);
        }

        if (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.PHAN_BO) {
            holder.tvTitleSoIDBBan.setVisibility(View.VISIBLE);
            holder.tvSoBBanKD.setVisibility(View.VISIBLE);

            holder.tvTitleIdBBanTamThoi.setVisibility(View.VISIBLE);
            holder.tvIdBBanTamThoi.setVisibility(View.VISIBLE);


            String SO_BBAN_KDINH = mListPB.get(position).getSO_BBAN_KDINH();
            holder.tvSoBBanKD.setText(SO_BBAN_KDINH);

            String SO_PBCT_MTB = mListPB.get(position).getSO_PBCT_MTB();
            holder.tvTitleIdBBanTamThoi.setText("Số biên bản phân bổ tạm thời: ");
            holder.tvIdBBanTamThoi.setText(SO_PBCT_MTB);
        }

//        String CHISO_THAO = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListKD.get(position).getCHISO_THAO() : mListPB.get(position).get();
//        holder.tvCSThao.setText(CHISO_THAO);
//
//        //hiện tại không cần hiển thị chỉ số tháo
//        holder.tvCSThao.setVisibility(View.INVISIBLE);

        String NAM_SX = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListKD.get(position).getNAM_SX() : mListPB.get(position).getNAM_SX();
        holder.tvNamSx.setText(NAM_SX);

        String MA_DVIQLY = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListKD.get(position).getMA_DVIQLY() : mListPB.get(position).getMA_DVIQLY();
        holder.tvMaDvi.setText(MA_DVIQLY);

        String NGAY_NHAP_MTB = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListKD.get(position).getNGAY_NHAP_MTB() : mListPB.get(position).getNGAY_NHAP_MTB();

        if (!isHistoryAdapter)
            holder.tvNgayNhap.setText(Common.convertDateSQLToDateUI(NGAY_NHAP_MTB));

        //xử lý nút ghim
        holder.btnGhim.setText("GHIM");
        holder.btnGhim.setClickable(true);
        holder.btnChon.setClickable(true);
        int valueInPixels = (int) mContext.getResources().getDimension(R.dimen._5sdp);
        holder.btnGhim.setPadding(valueInPixels, 0, 0, 0);
        holder.btnChon.setPadding(valueInPixels, 0, 0, 0);

        int TRANG_THAI_GHIM = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListKD.get(position).getTRANG_THAI_GHIM() : mListPB.get(position).getTRANG_THAI_GHIM();

        Drawable drawable = null;
        int color = 0;

        if (TRANG_THAI_GHIM == Common.TRANG_THAI_GHIM.CHUA_GHIM.getCode()) {
            drawable = sDrawableUnGhim;
            color = sWhiteColor;
        } else {
            drawable = sDrawableGhim;
            color = sLightColor;
        }
        int h = drawable.getIntrinsicHeight();
        int w = drawable.getIntrinsicWidth();
        drawable.setBounds(0, 0, w, h);
        holder.btnGhim.setCompoundDrawables(null, null, drawable, null);

        //xử lý nút chọn
        int getTRANG_THAI_CHON = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListKD.get(position).getTRANG_THAI_CHON() : mListPB.get(position).getTRANG_THAI_CHON();

        if (getTRANG_THAI_CHON == Common.TRANG_THAI_CHON.CHUA_CHON.getCode()) {
            drawable = sDrawableUnChon;
        } else {
            drawable = sDrawableChon;
        }
        h = drawable.getIntrinsicHeight();
        w = drawable.getIntrinsicWidth();
        drawable.setBounds(0, 0, w, h);
        holder.btnChon.setCompoundDrawables(null, null, drawable, null);

        if (menuBottomKD == Common.MENU_BOTTOM_KD.ALL) {
            //ẩn nút
            holder.btnXoa.setVisibility(View.VISIBLE);
            holder.btnGhim.setVisibility(View.VISIBLE);
            holder.btnChon.setVisibility(View.GONE);

            //xử lý dữ liệu gửi
            int CHON = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListKD.get(position).getCHON() : mListPB.get(position).getCHON();

            //nếu gửi thành công
            if (CHON == Common.CHON.GUI_THANH_CONG.getCode()) {
                //đổi màu đen khi thao tác với server
                color = sBlackColor;
                holder.btnGhim.setClickable(false);
                holder.btnGhim.setPadding(0, 0, 0, 0);
                holder.btnGhim.setClickable(false);
                holder.btnGhim.setCompoundDrawables(null, null, null, null);
                holder.btnGhim.setText(Common.CHON.findNameBy(CHON).getName());
            }

            //nếu gửi thất bại
            if (CHON == Common.CHON.GUI_THAT_BAI.getCode()) {
                color = sBlackColor;
                holder.btnGhim.setClickable(false);
                holder.btnGhim.setPadding(0, 0, 0, 0);
                holder.btnGhim.setClickable(false);
                holder.btnGhim.setCompoundDrawables(null, null, null, null);
                holder.btnGhim.setText(Common.CHON.findNameBy(CHON).getName());
            }

        }

        if (menuBottomKD == Common.MENU_BOTTOM_KD.DS_GHIM) {
            //ẩn và hiện nút
            holder.btnXoa.setVisibility(View.GONE);
            holder.btnGhim.setVisibility(View.VISIBLE);
            holder.btnChon.setVisibility(View.VISIBLE);


            //xử lý dữ liệu gửi
            int CHON = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListKD.get(position).getCHON() : mListPB.get(position).getCHON();
            //nếu gửi thành công
            if (CHON == Common.CHON.GUI_THANH_CONG.getCode()) {
                //đổi màu đen khi thao tác với server
                color = sBlackColor;
                holder.btnChon.setClickable(false);
//                paramsChon.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//                paramsGhim.addRule(RelativeLayout.LEFT_OF, R.id.btn_ghim);
                holder.btnChon.setPadding(0, 0, 0, 0);
                holder.btnChon.setClickable(false);
                holder.btnChon.setCompoundDrawables(null, null, null, null);
                holder.btnChon.setText(Common.CHON.findNameBy(CHON).getName());

            }

            //nếu gửi thất bại
            if (CHON == Common.CHON.GUI_THAT_BAI.getCode()) {
                color = sBlackColor;
                holder.btnChon.setClickable(false);
                holder.btnChon.setPadding(0, 0, 0, 0);
                holder.btnChon.setCompoundDrawables(null, null, null, null);
                holder.btnChon.setText(Common.CHON.findNameBy(CHON).getName());
            }

            if (CHON == Common.CHON.CHUA_GUI.getCode()) {
                color = sLightColor;
                holder.btnChon.setClickable(true);
//                holder.btnChon.setPadding(0, 0, 0, 0);
//                holder.btnChon.setClickable(false);
//                holder.btnChon.setCompoundDrawables(null, null, null, null);
                holder.btnChon.setText("CHỌN");
            }
        }
        holder.relativeLayout.setBackgroundColor(color);

        //nếu là history thì disable click các nút
        holder.tvInfoResult.setVisibility(View.GONE);
        if (isHistoryAdapter) {
            holder.btnChon.setVisibility(View.GONE);
            holder.btnGhim.setVisibility(View.GONE);
            holder.btnXoa.setVisibility(View.GONE);

            holder.tvInfoResult.setVisibility(View.VISIBLE);
            int ID = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListKD.get(position).getID_TBL_CTO_GUI_KD() : mListPB.get(position).getID_TBL_CTO_PB();
            //get infoResult
            String infoResult = mListener.interactionDataINFO_RESULT(ID, mTypeSessionHistory, mDateSessionHistory);

            holder.tvInfoResult.setText((StringUtils.isEmpty(infoResult)) ? "" : "Thông tin: " + infoResult);
        }
    }

    @Override
    public int getItemCount() {
        int size = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListKD.size() : mListPB.size();
        return size;
    }

//    public boolean isDsCtoGhim() {
//        return isDsCtoGhim;
//    }

    public class DsCtoViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout relativeLayout;
        public TextView tvSTT, tvSoCto, tvMaCto, tvMaCLoai, tvCSThao, tvMaDvi, tvNamSx, tvNgayNhap, tvSoBBanKD, tvTitleSoIDBBan, tvInfoResult, tvIdBBanTamThoi, tvTitleIdBBanTamThoi;

        public Button btnXoa, btnGhim, btnChon;

        public DsCtoViewHolder(View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_row_ds_cto);
            tvSTT = (TextView) itemView.findViewById(R.id.tv_STT);
            tvSoCto = (TextView) itemView.findViewById(R.id.tv_so_cto);
            tvMaCto = (TextView) itemView.findViewById(R.id.tv_ma_cto);
            tvMaCLoai = (TextView) itemView.findViewById(R.id.tv_ma_cloai);
            tvCSThao = (TextView) itemView.findViewById(R.id.tv_cs_thao);
            tvMaDvi = (TextView) itemView.findViewById(R.id.tv_ma_dvi);
            tvIdBBanTamThoi = (TextView) itemView.findViewById(R.id.tv_id_bban_phanbo_tamthoi);
            tvTitleIdBBanTamThoi = (TextView) itemView.findViewById(R.id.tv_title_id_bban_phanbo_tamthoi);

            tvNamSx = (TextView) itemView.findViewById(R.id.tv_nam_sx);
            tvNgayNhap = (TextView) itemView.findViewById(R.id.tv_ngay_nhap);
            tvSoBBanKD = (TextView) itemView.findViewById(R.id.tv_so_bban_kdinh);
            tvTitleSoIDBBan = (TextView) itemView.findViewById(R.id.tv_title_id_bban_kiemdinh);
            tvInfoResult = (TextView) itemView.findViewById(R.id.tv_info_result);
            btnXoa = (Button) itemView.findViewById(R.id.btn_delete);
            btnGhim = (Button) itemView.findViewById(R.id.btn_ghim);
            btnChon = (Button) itemView.findViewById(R.id.btn_chon);

            btnGhim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    mListener.clickBtnGhimRowCto(pos);
                }
            });

            btnChon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    mListener.clickBtnChonRowCto(pos);
                }
            });

            btnXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //gửi ra ngoài vị trí  list đó để xử lý
                    int pos = getAdapterPosition();
                    mListener.clickBtnXoa(pos);
                }
            });

            tvInfoResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.clickTvInfoResult(tvInfoResult.getText().toString());
                }
            });
        }
    }

    public interface OnDsCtoAdapterIteraction {
        void clickBtnGhimRowCto(int pos);

        void clickBtnXoa(int pos);

        void clickBtnChonRowCto(int pos);

        String interactionDataINFO_RESULT(int id, Common.TYPE_SESSION mTypeSessionHistory, String mDateSessionHistory);

        void clickTvInfoResult(String infoResult);
    }

    public void refreshListKD(List<CongToGuiKDProxy> congToProxies
    ) {
        if (congToProxies == null)
            return;
        mListKD.clear();
        mListKD.addAll(congToProxies);
        notifyDataSetChanged();
    }

    public void refreshListPB(List<CongToPBProxy> congToProxies
    ) {
        if (congToProxies == null)
            return;
        mListPB.clear();
        mListPB.addAll(congToProxies);
        notifyDataSetChanged();
    }
}
