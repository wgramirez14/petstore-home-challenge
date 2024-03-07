import http from "k6/http";
import { check, sleep , group} from "k6";
import { URLSearchParams } from 'https://jslib.k6.io/url/1.0.0/index.js';

const BASE_URL = __ENV.BASE_URL || 'https://petstore3.swagger.io';
const data = JSON.parse(open("./data.json"));
const params = {
  headers: {
    "Content-Type": "application/json",
  },
};

export const options = {
  thresholds: {
    http_req_failed: [{ threshold:'rate<0.10', abortOnFail: true}],
    http_req_duration: ['p(99)<1000'],
  },
  scenarios: {
    smoke: {
      exec: "crudPet",
      executor: "constant-vus",
      vus: 3,
      duration: '1m'
   },
   average_load: {
      exec: "crudPet",
      executor: "ramping-vus",
      stages: [
        { duration: "5m", target: 20 },
        { duration: "30m", target: 20 },
        { duration: "5m", target: 0 },
      ],
      startTime: '70s'
    },
    spike: {
      exec: "crudPet",
      executor: "ramping-vus",
      stages: [
        { duration: "2m", target: 2000 },
        { duration: "1m", target: 0 }
      ],
      startTime: '130s'
    },
    breaking: {
      exec: "crudPet",
      executor: "ramping-vus",
      stages: [
        { duration: "10s", target: 100 },
        { duration: "50s", target: 200 },
        { duration: "50s", target: 300 },
        { duration: "50s", target: 400 },
        { duration: "50s", target: 500 },
        { duration: "50s", target: 600 },
        { duration: "50s", target: 700 },
        { duration: "50s", target: 800 },
        { duration: "50s", target: 900 },
        { duration: "50s", target: 1000 }
      ],
      startTime: '5m'
    },
  }
};

export function crudPet() {
  group('01. Create a pet', () => {
    const payload = JSON.stringify(data);
    const res = http.post(`${BASE_URL}/api/v3/pet`, payload, params);

    if (check(res, { 'Pet created correctly': (r) => r.status === 200 })) {
    } else {
        console.log(`Unable to create a pet ${res.status} ${res.body}`);
        return;
    }
    sleep(1)
  });

  group('02. Update a pet', () => {
    const payload = JSON.stringify(data);
    const res = http.put(`${BASE_URL}/api/v3/pet`, payload, params);

    if (check(res, { 'Pet updated correctly': (r) => r.status === 200 })) {
    } else {
        console.log(`Unable to update a pet ${res.status} ${res.body}`);
        return;
    }
    sleep(1)
  });

  group('03. Delete a pet', () => {
    const res = http.del(`${BASE_URL}/api/v3/pet/10`, params);

    if (check(res, { 'Pet deleted correctly': (r) => r.status === 200 })) {
    } else {
        console.log(`Unable to delete a pet ${res.status} ${res.body}`);
        return;
    }
    sleep(1)
  });

  group('04. Get a pet by status', () => {
    const queryParams = new URLSearchParams([
      ['status', 'available']
    ]);
    const res = http.get(`${BASE_URL}/api/v3/pet/findByStatus?${queryParams.toString()}`, params);
    if (check(res, { 'Pet list got correctly': (r) => r.status === 200 })) {
    } else {
        console.log(`Unable to get pets ${res.status} ${res.body}`);
        return;
    }
    sleep(1)
  });
}

export function teardown() {
  console.log("Performance test finished")
}