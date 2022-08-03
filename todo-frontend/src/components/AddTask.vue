<template>
    <v-card>
        <v-card-title id="add-card-title" class="headline">
            Add a TO DO
        </v-card-title>
        <div class="pa-2 mt-2">
            <v-text-field label="Name"
                          v-model="name"
                          color="#58adbe"
                          autofocus
                          :rules="nameRules"
                          class="mx-2"
                          @keyup.enter="createTodo"
            />
            <v-text-field label="Description"
                          v-model="description"
                          color="#58adbe"
                          class="mx-2"
                          @keyup.enter="createTodo"
            />
            <v-card-actions>
                <v-spacer/>
                <v-btn id="confirm-add-task-btn" tile class="" @click="createTodo">
                    <v-icon>mdi-plus</v-icon>
                </v-btn>
            </v-card-actions>
        </div>
    </v-card>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';
import {TaskDTO} from "@/models/Task";

@Component
export default class AddTask extends Vue {
    name = '';
    description = '';

    nameRules = [
        (v: string) => !!v || 'Name is required',
        (v: string) => (v && v.length <= 30) || 'Name must be less than 30 characters'
    ];

    createTodo() {
        if (this.name) {
            const task = new TaskDTO();
            task.name = this.name;
            task.description = this.description;
            this.$emit('create', task);
            this.name = '';
            this.description = '';
        }
    }

}
</script>

<style scoped lang="scss">

#add-card-title {
    background-color: #58adbe;
    color: white;
}

#confirm-add-task-btn {
    color: #58adbe;
}

</style>
