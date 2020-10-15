package android.eservices.recyclerview;

import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder>{


    private List<GameViewModel> gameViewModelList = new ArrayList<>();
    private GameActionInterface gameActionInterface;

    public GameAdapter(GameActionInterface gameActionInterface){
        this.gameActionInterface = gameActionInterface;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent,false);

        return new GameViewHolder(view,gameActionInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        holder.bind(gameViewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return gameViewModelList.size();
    }

    public void bindGameViewModelList(List<GameViewModel> gameViewModelList) {
        this.gameViewModelList = gameViewModelList;
        notifyDataSetChanged();
    }

    public class GameViewHolder extends RecyclerView.ViewHolder {

        private TextView gameTitleTextView;
        private TextView gameDescriptionTextView;
        private ImageView gameImageImageView;
        private View view;
        private ImageButton gameClickImageButton;
        private ImageButton gameInfoClickImageButton;

        private GameActionInterface gameActionInterface;

        public GameViewHolder(View view, GameActionInterface gameActionInterface){
            super(view);
            this.view=view;
            gameClickImageButton = view.findViewById(R.id.game_button);
            gameInfoClickImageButton = view.findViewById(R.id.info_button);
            this.gameActionInterface=gameActionInterface;
            gameTitleTextView = view.findViewById(R.id.title_textview);
            gameDescriptionTextView = view.findViewById(R.id.description_textview);
            gameImageImageView = view.findViewById(R.id.icon_imageview);
        }

        public void bind(GameViewModel gameViewModel){
            gameTitleTextView.setText((gameViewModel.getTitle()));
            gameDescriptionTextView.setText((gameViewModel.getDescription()));
            //on utilise la library glide qui s'occupe de charger l'image sur internet qui gere le cache qui redimensionne etc
            Glide.with(view)
                    .load(gameViewModel.getImageUrl())
                    .into(gameImageImageView);

            gameClickImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gameActionInterface.onGameClicked(gameTitleTextView.getText().toString());
                }
            });
            gameInfoClickImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gameActionInterface.onGameInfoClicked(gameTitleTextView.getText().toString());
                }
            });
        }
    }
}
