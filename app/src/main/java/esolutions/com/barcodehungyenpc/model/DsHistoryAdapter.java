package esolutions.com.barcodehungyenpc.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import esolutions.com.barcodehungyenpc.R;
import esolutions.com.barcodehungyenpc.database.SqlConnect;
import esolutions.com.barcodehungyenpc.database.SqlDAO;
import esolutions.com.barcodehungyenpc.entity.HistoryProxy;
import esolutions.com.barcodehungyenpc.utils.Common;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class DsHistoryAdapter extends RecyclerView.Adapter<DsHistoryAdapter.DsHistoryViewHolder> {
    private SQLiteDatabase mDatabase;
    private SqlDAO mSqlDAO;
    private Context mContext;
    private List<HistoryProxy> mListHistoryProxies;
    private OnDsHistoryAdapterIteraction mListener;
    private static Drawable sDrawableDownload, sDrawableUpload;
    private static int sWhiteColor, sLightColor;
    private Common.MENU_BOTTOM_KD menuBottomKD;
    //phân biệt adapter đang sử dụng các công tơ được ghim = true
    // hoặc đang sử dụng tất cả các công tơ = false
//    private boolean isDsCtoGhim = false;

    public DsHistoryAdapter(Context mContext, List<HistoryProxy> mListCto
//            ,boolean isDsCtoGhim
    ) throws Exception {
        mDatabase = SqlConnect.getInstance(mContext).open();
        mSqlDAO = new SqlDAO(mDatabase, mContext);
        this.mContext = mContext;
        this.mListHistoryProxies = new ArrayList<>();
        this.mListHistoryProxies.addAll(mListCto);

        if (sDrawableDownload == null) {
            sDrawableDownload = ContextCompat.getDrawable(mContext, R.mipmap.ic_download_blue);
        }
        if (sDrawableUpload == null) {
            sDrawableUpload = ContextCompat.getDrawable(mContext, R.mipmap.ic_upload_orange);
        }

        sWhiteColor = ContextCompat.getColor(mContext.getApplicationContext(), R.color.white);
        sLightColor = ContextCompat.getColor(mContext.getApplicationContext(), R.color.primary_light);
//        this.isDsCtoGhim = isDsCtoGhim;

        if (mContext instanceof OnDsHistoryAdapterIteraction) {
            mListener = (OnDsHistoryAdapterIteraction) mContext;
        } else {
            throw new Exception("class must be implement OnDsCtoAdapterIteraction!");
        }
    }

    @Override
    public DsHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.row_rv_ds_history, parent, false);
        return new DsHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DsHistoryViewHolder holder, int position) {
        Common.runAnimationClickView(holder.relativeLayout, R.anim.twinking_view, Common.TIME_DELAY_ANIM);

        String DATE_SESSION = mListHistoryProxies.get(position).getDATE_SESSION();
        long longDATE_SESSION = Long.parseLong(DATE_SESSION);

        holder.tvDate.setText(Common.convertLongToDate(longDATE_SESSION, Common.DATE_TIME_TYPE.ddMMyyyyHHmmss));


        String TYPE_SESSION = mListHistoryProxies.get(position).getTYPE_SESSION();
        Common.TYPE_SESSION typeSession = Common.TYPE_SESSION.findNameBy(TYPE_SESSION);
        String INFO_SEARCH = mListHistoryProxies.get(position).getINFO_SEARCH();

        if(TYPE_SESSION.equals(Common.TYPE_SESSION.DOWNLOAD.getCode()))
        {
            holder.ibtnLogo.setImageDrawable(sDrawableDownload);
            //nếu download thì quan tâm tới info search
            holder.tvInfo.setText(INFO_SEARCH);
        }else {
            holder.ibtnLogo.setImageDrawable(sDrawableUpload);

            //nếu upload thì quan tâm tới số lượng bản upload

            int countSuccess = 0;
            try {
                countSuccess = mSqlDAO.countByDateSessionHistoryCtoByRESULT(DATE_SESSION, Common.TYPE_TBL_CTO.KD, typeSession, Common.TYPE_RESULT.SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int countError = 0;
            try {
                countError = mSqlDAO.countByDateSessionHistoryCtoByRESULT(DATE_SESSION, Common.TYPE_TBL_CTO.KD, typeSession, Common.TYPE_RESULT.ERROR);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(countError==0)
            {
                holder.tvInfo.setText(countSuccess+"/" +countSuccess);
            }else {
                holder.tvInfo.setText(countSuccess+"/"+(countError+countSuccess));
            }
        }

        holder.tvTitleSession.setText(Common.TYPE_SESSION.findNameBy(TYPE_SESSION).getTitle());

        String TYPE_RESULT = mListHistoryProxies.get(position).getTYPE_RESULT();

        Common.TYPE_RESULT sTYPE= Common.TYPE_RESULT.findNameBy(TYPE_RESULT);
        holder.tvMessageSession.setText(sTYPE.getTitle());

    }

    @Override
    public int getItemCount() {
        return mListHistoryProxies.size();
    }

    public void setMenuBottom(Common.MENU_BOTTOM_KD menuBottom) {
        this.menuBottomKD = menuBottom;
    }

//    public boolean isDsCtoGhim() {
//        return isDsCtoGhim;
//    }

    public class DsHistoryViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout relativeLayout;
        public TextView tvDate, tvMessageSession, tvTitleSession, tvInfo;
        ImageView ibtnLogo;
        public Button btnDetailHistory;

        public DsHistoryViewHolder(View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_row_ds_history);
            ibtnLogo = (ImageView) itemView.findViewById(R.id.iv_logo_session);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date_history);

            tvMessageSession = (TextView) itemView.findViewById(R.id.tv_message_session);
            tvTitleSession = (TextView) itemView.findViewById(R.id.tv_title_history);
            tvInfo = (TextView) itemView.findViewById(R.id.tv_info_history);

            btnDetailHistory = (Button) itemView.findViewById(R.id.btn_chi_tiet_history);

            btnDetailHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    mListener.clickBtnHistoryChiTiet(pos);
                }
            });
        }
    }

    public interface OnDsHistoryAdapterIteraction {

        void clickBtnHistoryChiTiet(int pos);

        int getCountCtoByDateByRESULT(String date_session, Common.TYPE_TBL_CTO typeTblCto, Common.TYPE_SESSION typeSession, Common.TYPE_RESULT typeResult);
    }

    public void refresh(List<HistoryProxy> historyProxies
//            , boolean isDsCtoGhim
    ) {
        if (historyProxies == null)
            return;
//        this.isDsCtoGhim = isDsCtoGhim;
        mListHistoryProxies.clear();
        mListHistoryProxies.addAll(historyProxies);
        notifyDataSetChanged();
    }
}
