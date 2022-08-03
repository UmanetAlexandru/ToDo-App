import HTTP_CLIENT from "@/services/HTTP_CLIENT";
import Task, {TaskDTO} from "@/models/Task";
import {AxiosResponse} from "axios";

export abstract class TaskService {

    private static readonly URL = 'tasks';

    static async getAll(): Promise<Task[]> {
        const response = await HTTP_CLIENT.get<Array<TaskDTO>>(this.URL);
        return response.data.map(dto => new Task(dto));
    }

    static async update(todo: Task): Promise<Task> {
        return await HTTP_CLIENT.put<TaskDTO>(`${this.URL}/${todo.id}`, todo)
            .then(res => new Task(res.data))
            .catch(err => {
                console.error(err);
                throw err;
            });
    }

    static async create(todo: Task): Promise<Task> {
        return await HTTP_CLIENT.post<TaskDTO>(this.URL, todo)
            .then(res => new Task(res.data))
            .catch(err => {
                console.error(err);
                throw err;
            });
    }

    static async delete(id: number): Promise<AxiosResponse> {
        return await HTTP_CLIENT.delete(this.URL + "/" + id)
            .then()
            .catch(err => {
                console.error(err);
                throw err;
            });
    }
}
