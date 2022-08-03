<template>
    <v-app id="app-container">
        <Main></Main>
    </v-app>
</template>

<script lang="ts">
import {Vue, Component} from 'vue-property-decorator';
import Main from "@/components/Main.vue";

@Component({
    components: {Main}
})
export default class App extends Vue {

}
</script>

<style>
    html {
        overflow: auto !important;
    }
</style>
