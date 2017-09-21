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
import esolutions.com.barcodehungyenpc.entity.CongToProxy;
import esolutions.com.barcodehungyenpc.utils.Common;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class DsCongToAdapter extends RecyclerView.Adapter<DsCongToAdapter.DsCtoViewHolder> {

    private Context mContext;
    private List<CongToProxy> mListCongToProxies;
    private OnDsCtoAdapterIteraction mListener;
    private static Drawable sDrawableUnGhim, sDrawableGhim, sDrawableChon, sDrawableUnChon;
    private static int sWhiteColor, sLightColor;
    private Common.MENU_BOTTOM_KD menuBottomKD;
    //phân biệt adapter đang sử dụng các công tơ được ghim = true
    // hoặc đang sử dụng tất cả các công tơ = false
//    private boolean isDsCtoGhim = false;

    public DsCongToAdapter(Context mContext, List<CongToProxy> mListCto
//            ,boolean isDsCtoGhim
    ) throws Exception {
        this.mContext = mContext;
        this.mListCongToProxies = new ArrayList<>();
        this.mListCongToProxies.addAll(mListCto);

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
//        this.isDsCtoGhim = isDsCtoGhim;

        if (mContext instanceof OnDsCtoAdapterIteraction) {
            mListener = (OnDsCtoAdapterIteraction) mContext;
        } else {
            throw new Exception("class must be implement OnDsCtoAdapterIteraction!");
        }
    }

    public void setMenuBottomKD(Common.MENU_BOTTOM_KD menuBottomKD) {
        this.menuBottomKD = menuBottomKD;
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
        holder.tvSoCto.setText(mListCongToProxies.get(position).getmSoCto());
        holder.tvMaCto.setText(mListCongToProxies.get(position).getmMaCto());
        holder.tvMaCLoai.setText(mListCongToProxies.get(position).getmMaChungLoai());

        holder.tvCSThao.setText(mListCongToProxies.get(position).getmChiSoThao());
        //hiện tại không cần hiển thị chỉ số tháo
        holder.tvCSThao.setVisibility(View.INVISIBLE);

        holder.tvNamSx.setText(mListCongToProxies.get(position).getmNamSX());
        holder.tvMaDvi.setText(mListCongToProxies.get(position).getMaDLuc());
        holder.tvNgayNhap.setText(Common.convertDateSQLToDateUI(mListCongToProxies.get(position).getNGAY_NHAP()));

        //một số khác biệt giữa ds ghim và ds tất cả
        if (menuBottomKD == Common.MENU_BOTTOM_KD.ALL) {
            //ẩn nút
            holder.btnChon.setVisibility(View.GONE);
            //set ALIGN_PARENT_RIGHT do xml không set được phải set trong code
            holder.btnGhim.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.btnGhim.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.btnGhim.setLayoutParams(params);

            holder.btnXoa.setVisibility(View.VISIBLE);
        }

        if (menuBottomKD == Common.MENU_BOTTOM_KD.DS_GHIM) {
            //ẩn nút
            holder.btnXoa.setVisibility(View.GONE);
            holder.btnChon.setVisibility(View.VISIBLE);
            //set ALIGN_PARENT_RIGHT do xml không set được phải set trong code
            holder.btnGhim.setVisibility(View.VISIBLE);

            RelativeLayout.LayoutParams paramsChon = (RelativeLayout.LayoutParams) holder.btnChon.getLayoutParams();
            paramsChon.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.btnChon.setLayoutParams(paramsChon);

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.btnGhim.getLayoutParams();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                params.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            }else {
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            }
            params.addRule(RelativeLayout.LEFT_OF, R.id.btn_chon);
            holder.btnGhim.setLayoutParams(params);
        }

        //xử lý nút ghim
        int getTRANG_THAI_GHIM = mListCongToProxies.get(position).getTRANG_THAI_GHIM();
        Drawable drawable = null;
        int color = 0;

        if (getTRANG_THAI_GHIM == Common.TRANG_THAI_GHIM.CHUA_GHIM.getCode()) {
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
        int getTRANG_THAI_CHON = mListCongToProxies.get(position).getTRANG_THAI_CHON();

        if (getTRANG_THAI_CHON == Common.TRANG_THAI_CHON.CHUA_CHON.getCode()) {
            drawable = sDrawableUnChon;
        } else {
            drawable = sDrawableChon;
        }
        h = drawable.getIntrinsicHeight();
        w = drawable.getIntrinsicWidth();
        drawable.setBounds(0, 0, w, h);
        holder.btnChon.setCompoundDrawables(null, null, drawable, null);

        //set Background row ghim
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
        return mListCongToProxies.size();
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
//                            , isDsCtoGhim
                    );
                }
            });
        }
    }

    public interface OnDsCtoAdapterIteraction {
        void clickBtnGhimRowCto(int pos);

        void clickBtnChiTiet(int pos);

        void clickBtnXoa(int pos
//                , boolean isDsCtoGhim
        );

        void clickBtnChonRowCto(int pos);
    }

    public void refresh(List<CongToProxy> congToProxies
//            , boolean isDsCtoGhim
    ) {
        if (congToProxies == null)
            return;
//        this.isDsCtoGhim = isDsCtoGhim;
        mListCongToProxies.clear();
        mListCongToProxies.addAll(congToProxies);
        notifyDataSetChanged();
    }
}
