<script setup lang="ts">
    import { ref } from 'vue';
    import CenteredLayout from '../layouts/CenteredLayout.vue';
    const email = ref('');
    const senha = ref('');

    async function enviarDados() {
        const login = {
            email: email.value,
            senha: senha.value
        };

        const request = await fetch("http://localhost:8080/api/auth/login", {
            method: "POST",
            headers: {
            "Content-Type": "application/json",  
            },
            body: JSON.stringify(login),
        });

        if (request.ok) {
            console.log('Login realizado com sucesso');
        } else {
            console.error('Falha no login');
            window.location.href="http://localhost:3000/home";
        }
    }
</script>

<template>

    <CenteredLayout>
        <v-sheet class="mx-auto" width="300">
            <v-form fast-fail @submit.prevent>
                    <v-text-field
                    v-model="email"
                    label="Email"
            >
                </v-text-field>

                <v-text-field
                    v-model="senha"
                    label="senha"
                >
                </v-text-field>

                <v-btn class="mt-2" type="submit" block @click="enviarDados">Submit</v-btn>
            </v-form>
        </v-sheet>
</CenteredLayout>
</template>