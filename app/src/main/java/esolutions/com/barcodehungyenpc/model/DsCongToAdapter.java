package esolutions.com.barcodehungyenpc.model;

import android.content.Context;
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
    //phân biệt adapter đang sử dụng các công tơ được ghim = true
    // hoặc đang sử dụng tất cả các công tơ = false
//    private boolean isDsCtoGhim = false;

    public DsCongToAdapter(Context mContext, List<CongToProxy> mListCto
//            ,boolean isDsCtoGhim
    ) throws Exception {
        this.mContext = mContext;
        this.mListCongToProxies = new ArrayList<>();
        this.mListCongToProxies.addAll(mListCto);
//        this.isDsCtoGhim = isDsCtoGhim;

        if (mContext instanceof OnDsCtoAdapterIteraction) {
            mListener = (OnDsCtoAdapterIteraction) mContext;
        } else {
            throw new Exception("class must be implement OnDsCtoAdapterIteraction!");
        }
    }

    @Override
    public DsCtoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.row_rv_ds_cto, parent, false);
        return new DsCtoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DsCtoViewHolder holder, int position) {
        Common.runAnimationClickViewScale(holder.relativeLayout, R.anim.twinking_view, Common.TIME_DELAY_ANIM);

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

//        int getmGhimCto = mListCongToProxies.get(position).getmGhimCto();

//        holder.fabGhim.setImageResource((getmGhimCto == 0) ? R.drawable.ic_unmark : R.drawable.ic_unmark);
//        floatingActionMenu.getMenuIconView().setImageResource(R.drawable.ic_check_white_24dp);

//        holder.fabGhim.setImageDrawable(mContext.getApplicationContext().getResources().getDrawable((getmGhimCto == 0) ? R.drawable.ic_book : R.drawable.ic_booked));
//        holder.fabGhim.setImageDrawable(mContext.getApplicationContext().getResources().getDrawable((getmGhimCto == 0) ? R.drawable.ic_unmark : R.drawable.ic_mark));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            holder.fabGhim.setBackground(mContext.getApplicationContext().getResources().getDrawable((getmGhimCto == 0) ? R.drawable.ic_unmark : R.drawable.ic_mark));
//        } else
//            holder.fabGhim.setBackgroundDrawable(mContext.getApplicationContext().getResources().getDrawable((getmGhimCto == 0) ? R.drawable.ic_unmark : R.drawable.ic_mark));
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
        public Button btnXoa;

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
//            fabGhim.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Common.runAnimationClickViewScale(view, R.anim.scale_view_pull, Common.TIME_DELAY_ANIM);
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
