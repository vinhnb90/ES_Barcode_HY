package esolutions.com.barcodehungyenpc.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    public void setHistoryAdapter(boolean historyAdapter) {
        isHistoryAdapter = historyAdapter;
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
        holder.tvMaDvi.setText(NGAY_NHAP_MTB);

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

        RelativeLayout.LayoutParams paramsGhim = (RelativeLayout.LayoutParams) holder.btnGhim.getLayoutParams();
        RelativeLayout.LayoutParams paramsChon = (RelativeLayout.LayoutParams) holder.btnChon.getLayoutParams();
        //một số khác biệt giữa ds ghim và ds tất cả
        if (menuBottomKD == Common.MENU_BOTTOM_KD.ALL) {
            //ẩn nút
            holder.btnXoa.setVisibility(View.VISIBLE);
            holder.btnGhim.setVisibility(View.VISIBLE);
            holder.btnChon.setVisibility(View.GONE);

            //set ALIGN_PARENT_RIGHT do xml không set được phải set trong code
            paramsGhim.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            valueInPixels = (int) mContext.getResources().getDimension(R.dimen._5sdp);
            paramsGhim.setMargins(valueInPixels, 0, 0, 0);
            holder.btnGhim.setLayoutParams(paramsGhim);

            //xử lý dữ liệu gửi
            int CHON = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListKD.get(position).getCHON() : mListPB.get(position).getCHON();

            //nếu gửi thành công
            if (CHON == Common.CHON.GUI_THANH_CONG.getCode()) {
                //đổi màu đen khi thao tác với server
                color = sBlackColor;
                holder.btnGhim.setClickable(false);
                paramsGhim.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                holder.btnGhim.setPadding(0, 0, 0, 0);
                holder.btnGhim.setClickable(false);
                holder.btnGhim.setCompoundDrawables(null, null, null, null);
                holder.btnGhim.setText(Common.CHON.findNameBy(CHON).getName());
                holder.btnGhim.setLayoutParams(paramsGhim);
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
            holder.btnGhim.setClickable(true);
            holder.btnChon.setClickable(true);
            holder.btnChon.setVisibility(View.VISIBLE);

            //set ALIGN_PARENT_RIGHT do xml không set được phải set trong code
            paramsChon.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.btnChon.setLayoutParams(paramsChon);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                paramsGhim.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            } else {
                paramsGhim.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            }
            paramsGhim.addRule(RelativeLayout.LEFT_OF, R.id.btn_chon);


            //xử lý dữ liệu gửi
            int CHON = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH) ? mListKD.get(position).getCHON() : mListPB.get(position).getCHON();
            //nếu gửi thành công
            if (CHON == Common.CHON.GUI_THANH_CONG.getCode()) {
                //đổi màu đen khi thao tác với server
                color = sBlackColor;
                holder.btnChon.setClickable(false);
                paramsChon.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                paramsGhim.addRule(RelativeLayout.LEFT_OF, R.id.btn_ghim);
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
                holder.btnChon.setClickable(false);
                holder.btnChon.setCompoundDrawables(null, null, null, null);
                holder.btnChon.setText(Common.CHON.findNameBy(CHON).getName());
            }

            //nếu là history thì disable click các nút
            if (isHistoryAdapter) {
                holder.btnChon.setClickable(false);
                holder.btnGhim.setClickable(false);
                holder.btnXoa.setClickable(false);
            }

        }


        //set Background row ghim
        holder.btnChon.setLayoutParams(paramsChon);
        holder.btnGhim.setLayoutParams(paramsGhim);
        holder.relativeLayout.setBackgroundColor(color);

//        floatingActionMenu.getMenuIconView().setImageResource(R.drawable.ic_check_white_24dp);

//        holder.fabGhim.setImageDrawable(mContext.getApplicationContext().getResources().getDrawable((getTRANG_THAI_GHIM == 0) ? R.drawable.ic_book : R.drawable.ic_booked));
//        holder.fabGhim.setImageDrawable(mContext.getApplicationContext().getResources().getDrawable((getTRANG_THAI_GHIM == 0) ? R.drawable.ic_unmark : R.drawable.ic_chon));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            holder.fabGhim.setBackground(mContext.getApplicationContext().getResources().getDrawable((getTRANG_THAI_GHIM == 0) ? R.drawable.ic_unmark : R.drawable.ic_chon));
//        } else
//            holder.fabGhim.setBackgroundDrawable(mContext.getApplicationContext().getResources().getDrawable((getTRANG_THAI_GHIM == 0) ? R.drawable.ic_unmark : R.drawable.ic_chon));
    }

    @Override
    public int getItemCount() {
        int size = (mKieuChuongTrinh == Common.KIEU_CHUONG_TRINH.KIEM_DINH)? mListKD.size(): mListPB.size();
        return size;
    }

//    public boolean isDsCtoGhim() {
//        return isDsCtoGhim;
//    }

    public class DsCtoViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout relativeLayout;
        public TextView tvSTT, tvSoCto, tvMaCto, tvMaCLoai, tvCSThao, tvMaDvi, tvNamSx, tvNgayNhap;
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
            tvNamSx = (TextView) itemView.findViewById(R.id.tv_nam_sx);
            tvNgayNhap = (TextView) itemView.findViewById(R.id.tv_ngay_nhap);

            btnXoa = (Button) itemView.findViewById(R.id.btn_delete);
            btnGhim = (Button) itemView.findViewById(R.id.btn_ghim);
            btnChon = (Button) itemView.findViewById(R.id.btn_chon);

//            fabGhim.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Common.runAnimationClickView(view, R.anim.scale_view_pull, Common.TIME_DELAY_ANIM);
//                    //gửi ra ngoài vị trí  list đó để xử lý
//                    int pos = getAdapterPosition();
//                    mListener.clickBtnGhimRowCto(pos);
//                }
//            });

            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //gửi ra ngoài vị trí  list đó để xử lý
                    int pos = getAdapterPosition();
                    mListener.clickBtnChiTiet(pos);
                }
            });

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
                    mListener.clickBtnXoa(pos
                    );
                }
            });
        }
    }

    public interface OnDsCtoAdapterIteraction {
        void clickBtnGhimRowCto(int pos);

        void clickBtnChiTiet(int pos);

        void clickBtnXoa(int pos);

        void clickBtnChonRowCto(int pos);
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
