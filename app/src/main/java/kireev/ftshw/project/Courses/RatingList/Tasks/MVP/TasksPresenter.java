package kireev.ftshw.project.Courses.RatingList.Tasks.MVP;

public class TasksPresenter {

    private TasksView view;
    private final TasksModel model;

    public TasksPresenter(TasksModel model) {
        this.model = model;
    }

    void attachView(TasksView tasksView) {
        view = tasksView;
    }

    void detachView() {
        view = null;
    }

    void showFromDatabase(){
        if (view != null){
            model.getTasks(view.getHomeworkId(),view.getTaskAdapter(),view.getRecyclerViewTasks());
        }
    }
}
