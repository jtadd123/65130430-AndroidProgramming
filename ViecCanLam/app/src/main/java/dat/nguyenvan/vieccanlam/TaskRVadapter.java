package dat.nguyenvan.vieccanlam;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskRVadapter extends RecyclerView.Adapter<TaskRVadapter.TaskItemViewHolder> {
    List<Tasks> dataSource;

    public TaskRVadapter(List<Tasks> dataSource) {
        this.dataSource = dataSource;
    }

    public final class TaskItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTenVCL;
        TextView tvNgayHetHan;
        TextView tvMessage;
        TextView tvPriority;
        View viewPriority;

        public TaskItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvTenVCL = itemView.findViewById(R.id.textViewTenVCL);
            tvNgayHetHan = itemView.findViewById(R.id.textViewThoiGian);
            tvMessage = itemView.findViewById(R.id.textViewMessage);
            tvPriority = itemView.findViewById(R.id.textViewPriority);
            viewPriority = itemView.findViewById(R.id.viewPriority);
        }

        @Override
        public void onClick(View v) {
            int vtClicked = getAdapterPosition();
            Tasks taskClicked = dataSource.get(vtClicked);
            Toast.makeText(v.getContext(), "Bạn vừa chọn việc " + taskClicked.getName(), Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public TaskItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Tasks task = dataSource.get(position);


        holder.tvTenVCL.setText(task.getName() != null ? task.getName() : "");


        String date = task.getDate();
        holder.tvNgayHetHan.setText(date != null && !date.equals("null") ? date : "Chưa đặt");


        String message = task.getMessage();
        if (message != null && !message.equals("null") && !message.isEmpty()) {
            holder.tvMessage.setText(message);
            holder.tvMessage.setVisibility(View.VISIBLE);
        } else {
            holder.tvMessage.setVisibility(View.GONE);
        }


        String priority = task.getPriority();
        int priorityColor;
        String priorityText;

        if (priority != null && !priority.equals("null")) {
            switch (priority) {
                case "1":
                    priorityColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.priority_high);
                    priorityText = "Cao";
                    break;
                case "2":
                    priorityColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.priority_medium);
                    priorityText = "Trung bình";
                    break;
                default:
                    priorityColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.priority_low);
                    priorityText = "Thấp";
                    break;
            }
        } else {
            priorityColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.priority_low);
            priorityText = "Thấp";
        }

        holder.viewPriority.setBackgroundColor(priorityColor);
        holder.tvPriority.setText(priorityText);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}