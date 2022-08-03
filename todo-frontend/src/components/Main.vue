<template>
    <v-card id="task-container" class="mx-auto mt-4">
        <v-app-bar dark elevation="1" color="#58adbe">
            <v-toolbar-title class="mx-auto">TO DO</v-toolbar-title>
        </v-app-bar>
        <v-container id="list-container" class="d-flex flex-column">
            <h2 v-if="tasks.length > 0" class="ml-4 mt-3">Upcoming</h2>
            <task v-for="task in tasks"
                  :task="task"
                  :key="task.id"
                  @delete="deleteById"
                  @update="update"/>

            <h2 v-if="completed.length > 0" class="ml-4 mt-3">Finished</h2>
            <completed-tasks v-for="task in completed"
                       :task="task"
                       :key="task.id"
                       @delete="deleteById"
                       @update="update"/>

            <v-dialog width="700" v-model="showAddDialog">
                <template v-slot:activator="{ on, attrs }">
                    <v-btn id="add-task-btn"
                           class="mt-auto mx-auto mt-7 mb-3"
                           v-bind="attrs"
                           v-on="on"
                           fab>
                        <v-icon>mdi-plus</v-icon>
                    </v-btn>
                </template>
                <add-task @create="createTodo"/>
            </v-dialog>
        </v-container>
    </v-card>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';
import Task from "@/components/Task.vue";
import {TaskService} from "@/services/TaskService";
import TodoEntity from "@/models/Task";
import CompletedTasks from "@/components/CompletedTasks.vue";
import AddTask from "@/components/AddTask.vue";

@Component({
    components: {AddTask, CompletedTasks, Task}
})
export default class Main extends Vue {
    tasks = new Array<TodoEntity>();
    completed = new Array<TodoEntity>();
    allTasks = new Array<TodoEntity>();
    showAddDialog = false;

    mounted() {
        TaskService.getAll().then(todos => {
            this.allTasks = todos;
            this.updateArrays();
        });
    }

    update(task: TodoEntity) {
        TaskService.update(task).then(task => {
            this.allTasks = this.allTasks.map(t => task.id == t.id ? task : t);
            this.updateArrays();
        })
    }

    deleteById(id: number) {
        TaskService.delete(id).then(() => {
            this.allTasks = this.allTasks.filter(task => task.id != id);
            this.updateArrays();
        });
    }

    updateArrays() {
        this.completed = [];
        this.tasks = [];
        this.allTasks.forEach(task => {
            if (task.completed) {
                this.completed.push(task);
            } else {
                this.tasks.push(task);
            }
        });
    }

    createTodo(task: TodoEntity) {
        this.showAddDialog = false;
        TaskService.create(task).then(task => {
            this.allTasks.push(task);
            this.tasks.push(task);
        });
    }
}
</script>

<style scoped lang="scss">
#task-container {
    background-image: linear-gradient(#72b0bb, #bbd7e0);
    color: white;
    min-height: 96%;
    width: 80%;
    max-width: 1000px;
}

#add-task-btn {
    color: #58adbe;
}

#list-container {
    height: 92%;
}
</style>
