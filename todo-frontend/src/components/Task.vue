<template>
    <v-card class="mt-3">
        <v-card-title class="headline">
            {{task.name}}
            <v-spacer></v-spacer>
            <v-btn icon @click="moveToDone(task)">
                <v-icon>mdi-check</v-icon>
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
import TaskEntity from "@/models/Task";

@Component
export default class Task extends Vue {
    @Prop() task!: TaskEntity;

    @Emit("update")
    moveToDone(task: TaskEntity): TaskEntity {
        task.completed = true;
        return task;
    }
}
</script>

<style scoped lang="scss">

</style>
