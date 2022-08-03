<template>
    <v-card color="#a0d0dc" dark class="mt-3">
        <v-card-title class="headline">
            <span class="text-decoration-line-through">{{task.name}}</span>
            <v-spacer></v-spacer>
            <v-btn icon @click="moveToNew(task)">
                <v-icon>mdi-arrow-up</v-icon>
            </v-btn>
            <v-btn icon class="ml-4" @click="$emit('delete', task.id)">
                <v-icon>mdi-close</v-icon>
            </v-btn>
        </v-card-title>
        <v-card-subtitle>
            {{task.description}}
        </v-card-subtitle>
    </v-card>
</template>

<script lang="ts">
import {Component, Emit, Prop, Vue} from 'vue-property-decorator';
import Task from "@/models/Task";
import TaskEntity from "@/models/Task";

@Component
export default class CompletedTasks extends Vue {
    @Prop() task!: Task;

    @Emit("update")
    moveToNew(task: TaskEntity): TaskEntity {
        task.completed = false;
        return task;
    }
}
</script>

<style scoped lang="scss">

</style>
